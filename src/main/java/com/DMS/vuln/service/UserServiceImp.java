package com.DMS.vuln.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DMS.vuln.dto.User;
import com.DMS.vuln.dto.UserDTO;
import com.DMS.vuln.repository.UserRepositoty;

@Service
public class UserServiceImp implements UserService {

    @Autowired 
    private UserRepositoty userRepositoty;

    public UserServiceImp(UserRepositoty userRepositoty) {
        this.userRepositoty = userRepositoty;
    }


    @Override
  public Boolean createUser(String username, String password, String role, String productAccess) {
        int result = userRepositoty.insertUser(username, password, role, productAccess);
        return result > 0;
    }

    @Override
    public List<User> getUsers() {
        
        userRepositoty.getUsers();
        return userRepositoty.getUsers();
    }

    @Override
    public boolean getUser(UserDTO user) {
        User storedUser = userRepositoty.getUser(user.getUsername());
        if(storedUser != null && storedUser.getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void updateUserRole(String username,String role,String productAccess ) {
         userRepositoty.updateUser(username, role, productAccess);

    }

    public String getUserRole(String username) {

        User storedUser = userRepositoty.getUser(username);
        if(storedUser != null) {
            return storedUser.getRole();
        }
        return null;
    }

    

    @Override
    public void deleteUser(String username) {
        userRepositoty.deleteUser(username);
    }

   

    
}
