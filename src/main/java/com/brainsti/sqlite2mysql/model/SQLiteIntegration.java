package com.brainsti.sqlite2mysql.model;

import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.brainsti.sqlite2mysql.model.entity.Contact;

public class SQLiteIntegration {
    private static SessionFactory sessionFactory = null;  
    private static ServiceRegistry serviceRegistry = null;  

    public SQLiteIntegration() {
    	configureSessionFactory();
	}
    
    private SessionFactory configureSessionFactory() throws HibernateException {  
        Configuration configuration = new Configuration();  
        configuration.configure();  
         
        Properties properties = configuration.getProperties();
         
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();          
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
         
        return sessionFactory;
    }
     
    @SuppressWarnings("unchecked")
	public List<Contact> listAllContacts() {
        // Configure the session factory
        configureSessionFactory();
         
        Session session = null;
        Transaction tx=null;
         
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
             
            // Creating Contact entity that will be save to the sqlite database
            Contact myContact = new Contact(3, "My Name", "my_email@email.com");
            Contact yourContact = new Contact(24, "Your Name", "your_email@email.com");
             
            // Saving to the database
            session.save(myContact);
            session.save(yourContact);
             
            // Committing the change in the database.
            session.flush();
            tx.commit();
             
            // Fetching saved data
            return session.createQuery("from Contact").list();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
		return null;
    }
    
    public List executeQuery(String query) {
    	Session session = null;
    	
    	try {
	    	session = sessionFactory.openSession();
	    	return session.createQuery(query).list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		session.close();	
    	}
    	return null;
    }
    
    public void save(Object registry) {
    	Session session = null;
    	
    	try {
	    	session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	session.save(registry);
	    	session.flush();
	    	session.getTransaction().commit();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		session.close();
    	}
    }
    
    public void executeSQLStatement(String sqlStatement) {
    	Session session = null;
    	
    	try {
	    	session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	session.createQuery(sqlStatement).executeUpdate();
	    	session.getTransaction().commit();
	    	session.flush();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		session.close();	
    	}
    }
}
