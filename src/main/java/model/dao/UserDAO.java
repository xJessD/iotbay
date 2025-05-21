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
         int customerID = rs.getInt("customerID"); // NEW
         String firstName = rs.getString("firstName");
         String lastName = rs.getString("lastName");
         String phoneNumber = rs.getString("phoneNumber");

         //  updated constructor with customerID
         return new User(customerID, firstName, lastName, email, password, phoneNumber);
      }

      return null;
   }

   // Add a user-data into the database
   public void addUser(String firstName, String lastName, String email, String password, String phoneNumber) throws SQLException { 
      st.executeUpdate("INSERT INTO User (firstName, lastName, email, password, phoneNumber) VALUES ('"
            + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', '" + phoneNumber + "')");
   }

   // update a user details in the database
   public void updateUser(String firstName, String lastName, String email, String password, String phoneNumber)
         throws SQLException {
      st.executeUpdate("UPDATE User SET firstName = '" + firstName + "', lastName = '" + lastName + "', email = '"
            + email + "', password = '" + password + "', phoneNumber = '" + phoneNumber + "' WHERE email = '" + email + "'");
   }

   // delete a user from the database
   public void deleteUser(String email) throws SQLException {
      st.execute("DELETE FROM User WHERE email = '" + email + "'");
   }
}
