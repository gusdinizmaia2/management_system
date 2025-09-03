package com.gustavo.managementsystem.Products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){

        return productRepository.findAll();
    }


}
