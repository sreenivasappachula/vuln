package com.DMS.vuln.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DMS.vuln.dto.Product;
import com.DMS.vuln.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {
 
  @Autowired 
 private  ProductRepository repo ;

   public ProductServiceImp(ProductRepository repo) {
        this.repo = repo;
    }

    public ProductServiceImp() {}

    @Override
    public Product viewProduct(Long id) {
        // In a real application, you would retrieve the product from a database
        // Here, we will just return a new Product object with the given ID
        return new Product(id, "Sample Product", "This is a sample product description.");
    }

    
    @Override
    public List<Product> viewAll() {
      
        List<Product> products =repo.getProducts() ;
        return products;
    }

    @Override
    public boolean create(Product product) {
        
                if((repo.insertProduct(product)==1)){
                    return true;
                }
                    return false;
    }





    @Override
    public long autoId() {
        // TODO Auto-generated method stub
        return Math.round(Math.random() * 1);
      
    }




    
    
}
