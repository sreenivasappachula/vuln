package com.DMS.vuln.dto;

import com.DMS.vuln.controller.Controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long REFERENCE_ID;
    @Column(name = "Username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "product_access", nullable = false)
    private String productAccess;

    public User() {
        
    }
    public User(long id, String username, String password, String role, String productAccess) {
        this.REFERENCE_ID = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.productAccess = productAccess;
    }   
    public String getProductAccess() {
        return productAccess;
    }
    public void setProductAccess(String productAccess) {
        this.productAccess = productAccess;
    }


    public long getId() {
        return REFERENCE_ID;
    }

    public void setId(long id) {
        this.REFERENCE_ID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
