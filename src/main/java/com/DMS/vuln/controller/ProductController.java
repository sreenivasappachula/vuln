package com.DMS.vuln.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.stereotype.Controller;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;

import com.DMS.vuln.dto.Document;
import com.DMS.vuln.dto.Product;
import com.DMS.vuln.dto.User;
import com.DMS.vuln.dto.UserDTO;
import com.DMS.vuln.service.DocumentServiceImp;
import com.DMS.vuln.service.ProductServiceImp;
import com.DMS.vuln.service.UserServiceImp;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProductController {

    private final ProductServiceImp productServiceImp;
    private final DocumentServiceImp documentService;
    private final UserServiceImp userService;

    ProductController(ProductServiceImp productServiceImp, DocumentServiceImp documentService, UserServiceImp userService) {
        this.productServiceImp = productServiceImp;
        this.documentService = documentService;
        this.userService = userService;
    }

      @GetMapping("/product-view")
            public String productView(@RequestParam String product, HttpSession session, Model model) {


                         System.out.println("Product name: " + product);
                    //get the related list of documents\
                    List<Document> documents = documentService.getAllDocuments(product);
                    //System.out.println("Documents for product " + product + ": " + documents.get(0).getDoc_Name());
                     model.addAttribute("role", session.getAttribute("role"));
                   
                    
                    model.addAttribute("product", product);
                    model.addAttribute("documents", documents);
                    
                    
                    return "productview";

            }


            @GetMapping("/all-products")
            public String allProducts(HttpSession session, Model model) {
                List<String> products = new ArrayList<>();

                if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {
                        for(Product s: productServiceImp.viewAll()) {
                            products.add(s.getName());
                        }
                }else if( (session.getAttribute("role") != null && session.getAttribute("role").equals("PM")) || (session.getAttribute("role") != null && session.getAttribute("role").equals("CONTRIBUTOR")) || (session.getAttribute("role") != null && session.getAttribute("role").equals("ENGINEER")) ) 
                    {    

                            List<String> productAccessList = new ArrayList<>();
                            userService.getUsers().forEach(user -> {
                                if(user.getUsername().equals(session.getAttribute("user"))) {
                                    productAccessList.add(user.getProductAccess());
                                }} );

                                        
                            for(String p: productAccessList) {
                                products.add(p.trim());
                            }
                    }
                    else {
                        return "redirect:/dashboard";
                    }
                
            
                model.addAttribute("products", products);

                return "allProducts";
            }

    }
