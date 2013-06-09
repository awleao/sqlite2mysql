package com.brainsti.sqlite2mysql.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.brainsti.sqlite2mysql.model.entity.Contact;

public class ContactTest {
	
	private Contact target;
	private SQLiteIntegration sqlite;
	
	@Before
	public void setUp() {
		sqlite = new SQLiteIntegration();
		target = new Contact(sqlite);
	}

	@Test
	public void isCorrectlyMapped(){
		List<Contact> contactList = target.listAll();
		assertEquals("Should have no entrys", contactList.size(), 0);
	}
	
	@Test
	public void shouldAddANewContactsToDB(){
        Contact awleaoContact = new Contact(sqlite);
        awleaoContact.setId(1);
        awleaoContact.setName("Avelar Leão");
        awleaoContact.setEmail("awleao@gmail.com");
        awleaoContact.save();
        
		List<Contact> contactList = target.listAll();
        assertEquals("Should have 1 entry", contactList.size(), 1);
        
        target.deleteAll();
	}
}
