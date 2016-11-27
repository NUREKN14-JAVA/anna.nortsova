package com.annanortsova.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;

import com.annanortsova.usermanagement.User;

import junit.framework.TestCase;

public class HsqldbUserDaoTest extends DatabaseTestCase  {
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	
	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Ann");
			user.setLastName("Nortsova");
			user.setDateOfBirthd(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testUpdate(){
		 try {
			 
			 User user = new User();
			 user.setId(1L);
			 String newFirstName = "Ann";
			 String newLastName = "Nortsova";
			 @SuppressWarnings("deprecation")
			 Date newDate = new Date(1996,11,14);
			 user.setFirstName(newFirstName);
			 user.setLastName(newLastName);
			 user.setDateOfBirthd(newDate);
			 dao.update(user);
			 user = dao.fin(user.getId());
			 assertEquals("Firstname fail", newFirstName, user.getFirstName());
			 assertEquals("Lastname fail", newLastName, user.getLastName());
			 assertEquals("Date fail", newDate, user.getDateOfBirthd());
	     } catch (DatabaseException e) {
	     	e.printStackTrace();
	     	fail(e.toString());
	     }
	 }

	 public void testDelete() {
		 try {
			 User user = new User();
			 long id = 1L;
			 user.setId(id);
			 dao.delete(user);
		 }catch(DatabaseException e){
			 e.printStackTrace();
		 	 fail(e.toString());
		 }
	}
	 
	public void testFind() {
		 try {
		 	User user = dao.fin(2L);
		 	assertNotNull(user);
			assertEquals(new Long(2L), user.getId());
			assertEquals("George", user.getFirstName());
			assertEquals("Bush", user.getLastName());
		 } catch (DatabaseException e) {
		 		e.printStackTrace();
		 		fail(e.toString());
		 }
		 long id = 3L;
		 try {
		 		dao.fin(id);   
		 } catch (DatabaseException e) {
		 		assertEquals(e.getMessage().toString(), "Could not find the user with id="+id);
		 }
	}
	


	public void testFindAll() {
	try {
		Collection collection = dao.findAll();
		assertNotNull("Collection is null",collection);
		assertEquals("collection.size. ",2, collection.size());
	} catch (DatabaseException e) {
		e.printStackTrace();
		fail(e.toString());
	}
	
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver",
				"Jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
		
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}
	}