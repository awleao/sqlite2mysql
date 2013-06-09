package com.brainsti.sqlite2mysql;

import java.util.List;


import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.brainsti.sqlite2mysql.model.SQLiteIntegration;
import com.brainsti.sqlite2mysql.model.entity.Contact;


public class SQLiteIntegrationTest {
	
	@Test
	@Ignore
	public void listAllContactsTest(){
		SQLiteIntegration sqlite = new SQLiteIntegration();
		List<Contact> contactList = sqlite.listAllContacts();
        for (Contact contact : contactList) {
        	assertEquals("Should have 3 entrys", contactList.size(), 3);
            System.out.println("Id: " + contact.getId() + " | Name:"  + contact.getName() + " | Email:" + contact.getEmail());
        }
	}
}
