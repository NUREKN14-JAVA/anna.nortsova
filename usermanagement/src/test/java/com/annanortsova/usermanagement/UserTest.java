package com.annanortsova.usermanagement;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase{
    
    private User user;
    
    private LocalDate dateOfBirthd;

    @Before
    protected void setUp() throws Exception {
        
        super.setUp();
        
        user = new User();
        
        dateOfBirthd = LocalDate.of(1941, 5, 24); 
                
        }

    @Test 
    public void testGetFullName() {
        
        user.setFirstName("Ann");
        user.setLastName("Nortsova");
        
        assertEquals("Nortsova, Ann",  user.getFullName());
        
        }

    @Test 
    public void testGetAge() {

        user.setDateOfBirthd(dateOfBirthd);
        
        int correctAnswer = LocalDate.now().getYear()-dateOfBirthd.getYear();
        
        assertEquals(correctAnswer, user.getAge());
        
        }


}
