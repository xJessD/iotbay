package model.dao;

import model.AccessLog;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccessLogDAO {
    private Statement st;
    private Connection conn;
    
    public AccessLogDAO(Connection conn) throws SQLException {
        this.conn = conn;
        st = conn.createStatement();
    }
    
    public void addAccessLog(AccessLog log) throws SQLException {
        String query = "INSERT INTO AccessLog (userId, loginDateTime) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, log.getUserId());
            stmt.setString(2, log.getLoginDateTime().toString());
            stmt.executeUpdate();
        }
    }
    
    public void updateLogoutTime(int userId, LocalDateTime logoutDateTime) throws SQLException {
        String query = "UPDATE AccessLog SET logoutDateTime = ? WHERE userId = ? AND logoutDateTime IS NULL";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, logoutDateTime.toString());
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    public void updateLogoutTime(String userID, LocalDateTime now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLogoutTime'");
        
    }

    public List<AccessLog> getUserLogs(int userId) throws SQLException {
        List<AccessLog> logs = new ArrayList<>();
        String query = "SELECT * FROM AccessLog WHERE userId = ? ORDER BY loginDateTime DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AccessLog log = new AccessLog(rs.getInt("userId"), LocalDateTime.parse(rs.getString("loginDateTime")));
                    log.setId(rs.getInt("id"));
                    String logoutDateTimeStr = rs.getString("logoutDateTime");
                    if (logoutDateTimeStr != null) {
                        log.setLogoutDateTime(LocalDateTime.parse(logoutDateTimeStr));
                    }
                    logs.add(log);
                }
            }
        }
        System.out.println("Debug: Retrieved " + logs.size() + " logs for user " + userId);
        return logs;
    }

    public List<AccessLog> getUserLogsByDateRange(int userId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<AccessLog> logs = new ArrayList<>();
        String query = "SELECT * FROM AccessLog WHERE userId = ? AND loginDateTime >= ? AND loginDateTime <= ? ORDER BY loginDateTime DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, startDate.toString());
            stmt.setString(3, endDate.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AccessLog log = new AccessLog(rs.getInt("userId"), LocalDateTime.parse(rs.getString("loginDateTime")));
                    log.setId(rs.getInt("id"));
                    String logoutDateTimeStr = rs.getString("logoutDateTime");
                    if (logoutDateTimeStr != null) {
                        log.setLogoutDateTime(LocalDateTime.parse(logoutDateTimeStr));
                    }
                    logs.add(log);
                }
            }
        }
        return logs;
    }

    /**
     * Delete a specific log entry by ID
     * @param logId The ID of the log to delete
     * @throws SQLException if a database error occurs
     */
    public void deleteLog(int logId) throws SQLException {
        String query = "DELETE FROM AccessLog WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, logId);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Delete all log entries for a specific user
     * @param userId The ID of the user whose logs should be deleted
     * @throws SQLException if a database error occurs
     */
    public void deleteAllUserLogs(int userId) throws SQLException {
        String query = "DELETE FROM AccessLog WHERE userId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Delete all log entries in the system (admin only)
     * @throws SQLException if a database error occurs
     */
    public void deleteAllLogs() throws SQLException {
        String query = "DELETE FROM AccessLog";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        }
    }
}