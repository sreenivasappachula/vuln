package com.DMS.vuln.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.DMS.vuln.dto.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UserRepositoty {

    @PersistenceContext
    private  EntityManager entityManager;

    public UserRepositoty() {

    }

    @Transactional  
    public int insertUser(String username, String password, String role, String productAccess) {
        String sql = "INSERT INTO users (username, password, role, product_access) VALUES ('"
                + username + "', '"
                + password + "', '"
                + role + "', '"
                + productAccess + "')";
        return entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = entityManager.createNativeQuery(sql, User.class).getResultList();
        return users;
    }

    @Transactional
    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE Username = '" + username + "'";
        return (User) entityManager.createNativeQuery(sql, User.class).getSingleResult();
    }


    

    @Transactional
    public void updateUser(String username,String role, String productAccess) {
        String sql = "update users set role = '" + role + "' where username = '" + username + "'";
        String sql2 = "update users set product_access = '" + productAccess + "' where username = '" + username + "'";
         entityManager.createNativeQuery(sql).executeUpdate();
         entityManager.createNativeQuery(sql2).executeUpdate();
        
    }

        

    @Transactional
    public void deleteUser(String username) {   
        String sql = "delete from users where username = '" + username + "'";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
    

        //user access and permissions
}
