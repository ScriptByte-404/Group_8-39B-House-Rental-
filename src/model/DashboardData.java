/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rojan
 */
public class DashboardData {

    public final String username;
    public final String email;
    public final String role;

    /**
     *
     */
    public final String status;

    public DashboardData(String username, String email, String role, String status) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}
