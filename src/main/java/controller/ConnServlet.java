package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import model.dao.*;
import model.dao.ShipmentDAO;

@WebServlet("/ConnServlet")
public class ConnServlet extends HttpServlet {

    private DBConnector db;

    private UserDAO manager;

    private AccessLogDAO accessLog;
    private PaymentDAO paymentManager;
    private ShipmentDAO shipmentManager;

    private Connection conn;

    @Override // Create and instance of DBConnector for the deployment session

    public void init() {

        try {

            db = new DBConnector();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override // Add the DBConnector, DBManager, Connection instances to the session

    protected void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        conn = db.openConnection();

        try {

            manager = new UserDAO(conn);
            accessLog = new AccessLogDAO(conn);
            shipmentManager = new ShipmentDAO(conn);
            session.setAttribute("shipmentDAO", shipmentManager);

            // Make sure to set both manager and accessLog as session attributes
            session.setAttribute("manager", manager);
            session.setAttribute("accessLog", accessLog);
            paymentManager = new PaymentDAO(conn);

        } catch (SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

        // export the DB managers to the view-session (JSPs)

        session.setAttribute("manager", manager);
        session.setAttribute("paymentDAO", paymentManager);

    }

    @Override // Destroy the servlet and release the resources of the application (terminate
              // also the db connection)

    public void destroy() {

        try {

            db.closeConnection();

            if (shipmentManager != null) {
            shipmentManager.close();
        }

        } catch (SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}