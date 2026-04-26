package com.DMS.vuln.repository;

import com.DMS.vuln.dto.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository  {


    @PersistenceContext
    private  EntityManager entityManager;
        
        @Transactional
        public  int insertProduct(Product product) {
            String sql = "INSERT INTO Product (id, name, description) VALUES ("
                    + product.getId() + ", '"
                    + product.getName() + "', '"
                    + product.getDescription() + "')";
               
            return entityManager.createNativeQuery(sql).executeUpdate();
        }   
        
      @Transactional
        public List<Product> getProducts() {

            String sql = "SELECT id,name,description FROM Product";

            List<Product> obj = entityManager.createQuery(sql, Product.class).getResultList();
                   //System.out.println("proddu..."+obj.get(0).getName()); 
            return obj;
        }



}