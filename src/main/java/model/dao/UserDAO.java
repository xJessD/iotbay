package model.dao;

import model.User;
import java.sql.*;

public class UserDAO {

   private Statement st;

   public UserDAO(Connection conn) throws SQLException {
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

   public void updateUser(User user) throws SQLException {
      updateUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhoneNumber());
   }

   // delete a user from the database
   public void deleteUser(String email) throws SQLException {
      st.execute("DELETE FROM User WHERE email = '" + email + "'");
   }

   public void deleteUser(int userID) throws SQLException {
      st.executeUpdate("DELETE FROM User WHERE userID = " + userID);
   }
}
