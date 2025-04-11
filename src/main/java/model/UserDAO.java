package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    //(https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm)
    // Instance of singleton data access object
    private static UserDAO instance;
    
    // (https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
    // Create map to store users
    private Map<String, User> users;
    
    // Default construtor
    private UserDAO() {
        users = new HashMap<>();
        
        // Create test user instance
        User testUser = new User();
        testUser.setFname("Test");
        testUser.setLname("User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        
        users.put(testUser.getEmail(), testUser);
    }
    
    //(https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm)
    // Get the singleton instance
    // return The UserDAO instance
    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
    
    // Save a user to UserDAO
    // return true if successful, else false
    public boolean save(User user) {
        if (user == null || user.getEmail() == null) {
            return false;
        }
        
        users.put(user.getEmail(), user);
        return true;
    }
    
    // Find a user by email
    // return User if found, else null
    public User findByEmail(String email) {
        return users.get(email);
    }
    

    // Authenticate a user with email and password
    // return User if successful, else null
    public User authenticate(String email, String password) {
        User user = findByEmail(email);
        
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        
        return null;
    }
    
    // List all users
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    // Delete a user searched by their email
    // return true if successful, false if no user is found
    public boolean delete(String email) {
        return users.remove(email) != null;
    }
}