package model.dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

   private Statement st;
   private Connection conn;

   public UserDAO(Connection conn) throws SQLException {
      this.conn = conn;
      st = conn.createStatement();
   }

   // Find user by email and password in the database
   public User findUser(String email, String password) throws SQLException {
      String query = "SELECT * FROM User WHERE email = '" + email + "' AND password = '" + password + "'";

      ResultSet rs = st.executeQuery(query);

      if (rs.next()) {
         String firstName = rs.getString("firstName");
         String lastName = rs.getString("lastName");
         String phoneNumber = rs.getString("phoneNumber");
         String accountType = rs.getString("role");
         Date createdDate = rs.getDate("createdDate");
         Date updatedDate = rs.getDate("lastUpdated");
         int userID = rs.getInt("userID");

         User newUser = new User(firstName, lastName, email, password, phoneNumber);
         newUser.setAccountType(accountType);
         newUser.setCreatedDate(createdDate);
         newUser.setUpdatedDate(updatedDate);
         newUser.setUserID(userID);

         return newUser;
      }

      return null;
   }

   public boolean emailExists(String email) throws SQLException {
      String fetch = "SELECT * FROM User WHERE email = '" + email + "'";
      ResultSet rs = st.executeQuery(fetch);
      return rs.next();
   }

   // Add a user-data into the database
   public void addUser(String firstName, String lastName, String email, String password, String phoneNumber) throws SQLException { 
      st.executeUpdate("INSERT INTO User (firstName, lastName, email, password, phoneNumber) VALUES ('"
            + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', '" + phoneNumber + "')");
   }

   // Add a user-data into the database with account type
   public void addUser(String firstName, String lastName, String email, String password, String phoneNumber, String accountType) throws SQLException {
      st.executeUpdate("INSERT INTO User (firstName, lastName, email, password, phoneNumber, role) VALUES ('"
            + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', '" + phoneNumber + "', '" + accountType + "')");
   }



   // update a user details in the database
   public void updateUser(String firstName, String lastName, String email, String password, String phoneNumber)
         throws SQLException {
      st.executeUpdate("UPDATE User SET firstName = '" + firstName + "', lastName = '" + lastName + "', email = '"
            + email + "', password = '" + password + "', phoneNumber = '" + phoneNumber + "' WHERE email = '" + email + "'");
   }

   /**
    * Update a user's information in the database
    * @param user The user object with updated information
    * @throws SQLException if a database error occurs
    */
   public void updateUser(User user) throws SQLException {
      String query = "UPDATE User SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, role = ? WHERE userID = ?";
    
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setString(3, user.getEmail());
      
      // Handle null phone number
      if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
          stmt.setNull(4, Types.VARCHAR);
      } else {
          stmt.setString(4, user.getPhoneNumber());
      }
      stmt.setString(5, user.getAccountType());
      stmt.setInt(6, user.getUserID());
    
      stmt.executeUpdate();
   }

   // delete a user from the database
   public void deleteUser(String email) throws SQLException {
      st.execute("DELETE FROM User WHERE email = '" + email + "'");
   }

   public void deleteUser(int userID) throws SQLException {
      st.executeUpdate("DELETE FROM User WHERE userID = " + userID);
   }

   /**
    * Fetch all users from the database
    * @return List of all users
    * @throws SQLException if a database error occurs
    */
   public List<User> fetchAllUsers() throws SQLException {
       List<User> users = new ArrayList<>();
       String query = "SELECT * FROM User ORDER BY userID";
       ResultSet rs = st.executeQuery(query);
       
       while (rs.next()) {
           int userID = rs.getInt("userID");
           String firstName = rs.getString("firstName");
           String lastName = rs.getString("lastName");
           String email = rs.getString("email");
           String password = rs.getString("password");
           String phoneNumber = rs.getString("phoneNumber");
           String accountType = rs.getString("role");
           Date createdDate = rs.getDate("createdDate");
           
           User user = new User(firstName, lastName, email, password, phoneNumber);
           user.setUserID(userID);
           user.setAccountType(accountType);
           user.setCreatedDate(createdDate);
           
           users.add(user);
       }
       
       return users;
   }

   /**
    * Search for users by name or email
    * @param searchTerm The search term to match against name or email
    * @return List of matching users
    * @throws SQLException if a database error occurs
    */
   public List<User> searchUsers(String searchTerm) throws SQLException {
       List<User> users = new ArrayList<>();
       String query = "SELECT * FROM User WHERE firstName LIKE ? OR lastName LIKE ? OR email LIKE ? ORDER BY userID";
       
       PreparedStatement stmt = conn.prepareStatement(query);
       String searchPattern = "%" + searchTerm + "%";
       stmt.setString(1, searchPattern);
       stmt.setString(2, searchPattern);
       stmt.setString(3, searchPattern);
       
       ResultSet rs = stmt.executeQuery();
       
       while (rs.next()) {
           int userID = rs.getInt("userID");
           String firstName = rs.getString("firstName");
           String lastName = rs.getString("lastName");
           String email = rs.getString("email");
           String password = rs.getString("password");
           String phoneNumber = rs.getString("phoneNumber");
           String accountType = rs.getString("role");
           Date createdDate = rs.getDate("createdDate");
           
           User user = new User(firstName, lastName, email, password, phoneNumber);
           user.setUserID(userID);
           user.setAccountType(accountType);
           user.setCreatedDate(createdDate);
           
           users.add(user);
       }
       
       return users;
   }

   /**
    * Get a user by their ID
    * @param userId The ID of the user to retrieve
    * @return The user with the specified ID, or null if not found
    * @throws SQLException if a database error occurs
    */
   public User getUserById(int userId) throws SQLException {
       String query = "SELECT * FROM User WHERE userID = ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       stmt.setInt(1, userId);
       
       ResultSet rs = stmt.executeQuery();
       
       if (rs.next()) {
           String firstName = rs.getString("firstName");
           String lastName = rs.getString("lastName");
           String email = rs.getString("email");
           String password = rs.getString("password");
           String phoneNumber = rs.getString("phoneNumber");
           String accountType = rs.getString("role");
           Date createdDate = rs.getDate("createdDate");
           
           User user = new User(firstName, lastName, email, password, phoneNumber);
           user.setUserID(userId);
           user.setAccountType(accountType);
           user.setCreatedDate(createdDate);
           
           return user;
       }
       
       return null;
   }

   /**
    * Get a user by their email
    * @param email The email of the user to retrieve
    * @return The user with the specified email, or null if not found
    * @throws SQLException if a database error occurs
    */
   public User getUserByEmail(String email) throws SQLException {
       String query = "SELECT * FROM User WHERE email = ?";
       PreparedStatement stmt = conn.prepareStatement(query);
       stmt.setString(1, email);
       
       ResultSet rs = stmt.executeQuery();
       
       if (rs.next()) {
           int userID = rs.getInt("userID");
           String firstName = rs.getString("firstName");
           String lastName = rs.getString("lastName");
           String password = rs.getString("password");
           String phoneNumber = rs.getString("phoneNumber");
           String accountType = rs.getString("role");
           Date createdDate = rs.getDate("createdDate");
           
           User user = new User(firstName, lastName, email, password, phoneNumber);
           user.setUserID(userID);
           user.setAccountType(accountType);
           user.setCreatedDate(createdDate);
           
           return user;
       }
       
       return null;
   }
}