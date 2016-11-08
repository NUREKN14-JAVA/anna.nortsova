package com.annanortsova.usermanagement;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;


public class UserTest extends TestCase {
	private User user;
	private Date dateOfBirthd;
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1996, Calendar.NOVEMBER, 14);
		dateOfBirthd = calendar.getTime();
	}

	public void testGetFullName() {
		user.setFirstName("Anna");
		user.setLastName("Nortsova");
		assertEquals("Nortsova, Anna", user.getFullName());
		}
	public void testGetAge() {
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(2016-1996, user.getAge());
	}
}
