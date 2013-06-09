package com.brainsti.sqlite2mysql.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brainsti.sqlite2mysql.model.SQLiteIntegration;

@Entity
@Table(name = "contact")
public class Contact {
    
	private SQLiteIntegration databaseConnection;
	
	private Integer id;
    private String name;
    private String email;
	
 
    public Contact() {
 
    }

    public Contact(SQLiteIntegration databaseConnection) {
    	this.databaseConnection = databaseConnection;
    }
    
    public Contact(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
 
    @Id
    public Integer getId() {
        return this.id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return this.name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Contact> listAll() {
    	return databaseConnection.executeQuery("from Contact");
    }
    
    public void save() {
    	databaseConnection.save(this);
    }
    
    public void deleteAll() {
    	databaseConnection.executeSQLStatement("delete from Contact");
    }
}
