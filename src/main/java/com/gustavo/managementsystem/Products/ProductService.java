package com.gustavo.managementsystem.Products;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){

        return productRepository.findAll();
    }

    public Optional<Product> findAllProductsForSupplier(String supplier){

        var formatUUID = UUID.fromString(supplier);

        return productRepository.findAllBySupplier_Id(formatUUID);
    }
 
    public Optional<Product> findProductById(Long productId){
        return productRepository.findById(productId);
    }

    public Optional<Product> findProductByName(String productName){
        return productRepository.findByName(productName);
    }

    public Product createProduct(Product body){

        return productRepository.save(body);
    }

    public Optional<Product> updateProduct(Long productId, Map<String,String> body){

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        
        body.forEach((key, value) -> {
            switch (key) {
                case "name" -> product.setName(value);
                case "description" -> product.setDescription(value);
                case "price" -> product.setPrice(Double.parseDouble(value));
                case "quantity" -> product.setQuantity(Integer.parseInt(value));
            }
        });

        var update = productRepository.save(product);

        return Optional.of(update);
    }

    public void removeProduct(Long productId){
        productRepository.deleteById(productId); 
    }


}
