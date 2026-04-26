package com.DMS.vuln.service;

import java.util.List;

import com.DMS.vuln.dto.User;
import com.DMS.vuln.dto.UserDTO;

public interface UserService {
    
    Boolean createUser(String username, String password, String role,String productAccess);
    List<User> getUsers();
    public boolean getUser(UserDTO user);
    public void updateUserRole(String username,String role,String productAccess);
    public void deleteUser(String username);
   public String getUserRole(String username);

}
