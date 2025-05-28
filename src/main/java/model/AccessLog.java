package model;

import java.time.LocalDateTime;

public class AccessLog {
    private int id;
    private int userId;
    private LocalDateTime loginDateTime;
    private LocalDateTime logoutDateTime;

    public AccessLog(int userId, LocalDateTime loginDateTime) {
        this.userId = userId;
        this.loginDateTime = loginDateTime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(LocalDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public LocalDateTime getLogoutDateTime() {
        return logoutDateTime;
    }

    public void setLogoutDateTime(LocalDateTime logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }
}