package controller;

import java.sql.*;

import java.util.*;

import java.util.logging.*;
import model.*;
import model.dao.*;

public class TestDB {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            UserDAO db = new UserDAO(conn);

            User user = db.findUser("test@t.com", "123456");

            db.deleteUser("jw@hello.com");

            db.addUser("Jane", "Watson", "jw@hello.com", "123456", "");

            User user2 = db.findUser("jw@hello.com", "123456");

            System.out.println("User found: " + user2.getFirstName());

            


            connector.closeConnection();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
