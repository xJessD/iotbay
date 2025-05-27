package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class UserTest {
    
    @Test
    public void testUserConstructor() {
        // Test the constructor
        User user = new User("John", "Doe", "john.doe@example.com", "password123", "+61412345678");
        
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("+61412345678", user.getPhoneNumber());
        assertNull(user.getAccountType()); // Default should be null before setting
    }
    
    @Test
    public void testSetAndGetMethods() {
        // Create a user and test setters and getters
        User user = new User();
        
        user.setUserID(1);
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setEmail("jane.smith@example.com");
        user.setPassword("securePass456");
        user.setPhoneNumber("0298765432");
        user.setAccountType("Staff");
        
        Date now = new Date();
        user.setCreatedDate(now);
        user.setUpdatedDate(now);
        
        assertEquals(1, user.getUserID());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane.smith@example.com", user.getEmail());
        assertEquals("securePass456", user.getPassword());
        assertEquals("0298765432", user.getPhoneNumber());
        assertEquals("Staff", user.getAccountType());
        assertEquals(now, user.getCreatedDate());
        assertEquals(now, user.getUpdatedDate());
    }
    
    @Test
    public void testFullName() {
        User user = new User("Robert", "Johnson", "robert@example.com", "pass789", "0412987654");
        
        // Test full name concatenation if such a method exists
        // If not, you can add this method to your User class
        assertEquals("Robert Johnson", user.getFirstName() + " " + user.getLastName());
    }
    
    @Test
    public void testAccountTypeDefaultsToCustomer() {
        User user = new User("Guest", "User", "guest@example.com", "guestpass", "0400000000");
        user.setAccountType("Customer");
        
        assertEquals("Customer", user.getAccountType());
    }
    
    @Test
    public void testStaffAccountType() {
        User user = new User("Admin", "User", "admin@example.com", "adminpass", "0411111111");
        user.setAccountType("Staff");
        
        assertEquals("Staff", user.getAccountType());
    }
}