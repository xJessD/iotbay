package model;

import java.io.Serializable;

public class Staff extends User implements Serializable {

    // Staff specific variables
    private String roleInCompany;

    public Staff() {}

    public Staff(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password, phoneNumber);
    }

    public Staff(String firstName, String lastName, String email, String password, String phoneNumber, String roleInCompany) {
        super(firstName, lastName, email, password, phoneNumber);
        this.roleInCompany = roleInCompany;
    }

    public String getRoleInCompany() {
        return roleInCompany;
    }

    public void setRoleInCompany(String roleInCompany) {
        this.roleInCompany = roleInCompany;
    }

    
}
