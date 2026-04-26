package com.DMS.vuln.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DMS.vuln.dto.Document;
import com.DMS.vuln.dto.Product;
import com.DMS.vuln.dto.User;
import com.DMS.vuln.dto.UserDTO;
import com.DMS.vuln.dto.DocumentDTO;
import com.DMS.vuln.service.DocumentService;
import com.DMS.vuln.service.ProductServiceImp;
import com.DMS.vuln.service.UserServiceImp;

import org.springframework.beans.factory.annotation.Value;


@RestController
@RequestMapping("/api")
public class Controller {
    
    private  LoginController loginController;
    
    @Value("\\uploads")
    private String uploadDir;

    private final ProductServiceImp productService ;
    private final UserServiceImp userService;
    private final DocumentService documentService;





    Controller(ProductServiceImp productService, UserServiceImp userService, DocumentService documentService, LoginController loginController) {
        this.productService = productService;
        this.userService = userService;
        this.documentService = documentService;
        this.loginController = loginController;
    }


    @PostMapping("/products")
    public boolean createProduct(@RequestBody Product product) {
        // Code to create a new product

           System.out.println("Creating product: " + product.getName() + " with description: " + product.getDescription());
           return (productService.create(product)?true: false);
        
           
    }

    @GetMapping("/view-products")
    public  List<Product>getAllProducts() {
        // Code to retrieve all products
        return productService.viewAll();
      
    }

    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDTO user) {
        // Code to create a new user
        //System.out.println("Received user data: " + user);
        userService.createUser(user.getUsername(), user.getPassword(), user.getRole(), user.getProductAccess());
        System.out.println("User created: " + user.getUsername() + ", Role: " + user.getRole() + ", Product Access: " + user.getProductAccess());
        return "User created successfully";
    } 

  

    @PostMapping("/update-user-role")
    public String updateUserRole(@RequestBody UserDTO user) {

        // Code to update user role
        
        userService.updateUserRole(user.getUsername(), user.getRole(), user.getProductAccess());
        return "User role updated successfully";
    }



   @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Document upload(
            @RequestPart("data") DocumentDTO request,
            @RequestPart("file") MultipartFile file
    ) {
        
        System.out.println("Received upload request: " + request.getDocNumber() + ", " + request.getDocName() + ", " + request.getDescription()+", "+request.getProductName()); 
        String username =  loginController.newSession.getAttribute("user").toString();

            // ⏱️ server-side timestamp
            LocalDateTime now = LocalDateTime.now();

       
                    try {
                        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

                        if (!Files.exists(uploadPath)) {
                            Files.createDirectories(uploadPath); // WHY: ensure path exists
                        }

                        String fileName = System.currentTimeMillis() + "_" +
                                new File(file.getOriginalFilename()).getName();

                        Path filePath = uploadPath.resolve(fileName);

                    System.out.println("Final file path: " + filePath);

                        file.transferTo(filePath.toFile());
                            
                // VERIFY
                        if (!Files.exists(filePath)) {
                            throw new RuntimeException("File NOT saved!");
                        }
                               Document entity = new Document(request.getDocNumber(), request.getDocName(), request.getDescription(), username, filePath.toString(), now.toString(), now.toString(), request.getProductName());
                                // entity.setDoc_NUMBER(request.getDoc_NUMBER());
                                // entity.setDoc_Name(request.getDoc_Name());
                                // entity.setDescription(request.getDescription());
                                // entity.setLocalFilePath(uploadDir + "\\" + fileName);
                                // entity.setDocumentAuthor(username);
                                // entity.setCreatedDate(now.toString());
                                // entity.setModifiedDate(now.toString());

                                System.out.println("Document entity built: " + entity.toString());

                                // 📤 save to 
                               if(documentService.createDocument(entity)) {
                                    // Document created successfully
                                
                                System.out.println("metadata saved in Database");
                               }


                    } catch (Exception e) {
                        throw new RuntimeException("Upload failed: " + e.getMessage());
                    }

        

            return null;

      
    }

}
