package com.DMS.vuln.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.DMS.vuln.service.DocumentServiceImp;
import com.DMS.vuln.service.ProductServiceImp;
import com.DMS.vuln.service.UserServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class Dashboard {
    
     private final UserServiceImp userService;
     private final ProductServiceImp productServiceImp;
     private final DocumentServiceImp documentService;

            class UserDTO{
                private String username;
                private String role;
                private String productAccess;

                public UserDTO(String username, String role, String productAccess) {
                    this.username = username;
                    this.role = role;
                    this.productAccess = productAccess;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }
                public String getProductAccess() {
                    return productAccess;
                }
                public void setProductAccess(String productAccess) {
                    this.productAccess = productAccess;
                }
            }      

    public Dashboard(UserServiceImp userService, ProductServiceImp productServiceImp,DocumentServiceImp documentService) {
        this.userService = userService;
        this.productServiceImp = productServiceImp; 
        this.documentService = documentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request,Model model) {

             HttpSession oldSession = request.getSession(false);
            if (oldSession == null) {
                return "login-page";
            }
                String username = (String) oldSession.getAttribute("user");
                String role = (String) oldSession.getAttribute("role");
                model.addAttribute("user", username);
                model.addAttribute("role", role);
            return "dashboard";
        }

    @GetMapping("/login-page")
    public String loginPage() {

        return "login-page";
    }

      @GetMapping("/user-management")
    public String userManagement(HttpSession session, Model model) {
        // Code to retrieve all users
        
        //if user is admin then only return the list of users otherwise return empty list or error message
       
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("user", session.getAttribute("user"));
        System.out.println("User in session: " + session.getAttribute("user") + ", Role: " + session.getAttribute("role"));

        
        List<UserDTO> userData = new ArrayList<>();
        
       userService.getUsers().forEach(user -> {
            userData.add(new UserDTO(user.getUsername(), user.getRole(), user.getProductAccess()));
            System.out.println("User: " + user.getUsername() + ", Role: " + user.getRole());
        });
        
      
        List<String> headers = Arrays.asList("Users", "Product Access", "Role");
       
       
        model.addAttribute("headers", headers);
        model.addAttribute("userData", userData);
      

            return "userManagement";

    }

          

}
