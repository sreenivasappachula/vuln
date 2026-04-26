package com.DMS.vuln.service;
import java.util.List;

import com.DMS.vuln.dto.Product;

public interface ProductService {


    List<Product> viewAll();

    boolean create(Product product);

    Product viewProduct(Long id);

    long autoId();

}
