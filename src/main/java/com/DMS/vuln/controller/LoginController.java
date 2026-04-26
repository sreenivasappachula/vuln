package com.DMS.vuln.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DMS.vuln.dto.UserDTO;
import com.DMS.vuln.service.UserServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
     private final UserServiceImp userService;
    
        
    public HttpSession newSession ;




    LoginController(UserServiceImp userService) {
        this.userService = userService; 

    }

    @PostMapping("/api/login/login-page")
    public String login(@RequestBody UserDTO user,HttpServletRequest request) {

        if(userService.getUser(user)) {
               
            
             HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

             newSession = request.getSession(true);
            
      
            newSession.setAttribute("user", user.getUsername());
            newSession.setAttribute("role", userService.getUserRole(user.getUsername()));
             String role = userService.getUserRole(user.getUsername());
            System.out.println("User " + user.getUsername() + " logged in with role: " + user.getRole());
     
            newSession.setMaxInactiveInterval(60); 
            return "redirect:/dashboard"; 
        }
    return "redirect:/login-page"; 
      
    }



}
