package controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {
    
    private Validator validator;
    
    @Before
    public void setUp() {
        validator = new Validator();
    }
    
    @Test
    public void testValidateEmail() {
        // Valid email formats
        assertTrue(validator.validateEmail("user@example.com"));
        assertTrue(validator.validateEmail("user-name@example.com"));
        assertTrue(validator.validateEmail("user_name@example.com"));
        
        // Invalid email formats
        assertFalse(validator.validateEmail(""));
        assertFalse(validator.validateEmail("userexample.com")); 
        assertFalse(validator.validateEmail("user@")); 
        assertFalse(validator.validateEmail("@example.com"));
        assertFalse(validator.validateEmail("user@.com"));
    }
    
    @Test
    public void testValidatePassword() {
        // Valid passwords
        assertTrue(validator.validatePassword("password123"));
       assertTrue(validator.validatePassword("12345678"));
        
        // Invalid passwords
        assertFalse(validator.validatePassword(""));        

    }
    
    @Test
    public void testValidatePhoneNumber() {
        // Valid Australian phone numbers
        assertTrue(validator.validatePhoneNumber("+61412345678"));
        assertTrue(validator.validatePhoneNumber("0412345678"));
        assertTrue(validator.validatePhoneNumber("(02) 9123 4567"));
        assertTrue(validator.validatePhoneNumber("02 9123 4567"));
        
        // Invalid phone numbers
        assertFalse(validator.validatePhoneNumber("123")); // Too short
        assertFalse(validator.validatePhoneNumber("abcdefghij")); // Non-numeric
        assertFalse(validator.validatePhoneNumber("+1234567890123456")); // Too long
    }
    
    @Test
    public void testValidateName() {
        // Valid names
        assertFalse(validator.validateName("123")); // Numeric
        assertFalse(validator.validateName("John123")); // Alphanumeric
    }
}