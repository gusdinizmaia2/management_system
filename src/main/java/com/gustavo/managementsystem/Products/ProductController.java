package com.gustavo.managementsystem.Products;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){

        return productService.findAllProducts();
    }

}
