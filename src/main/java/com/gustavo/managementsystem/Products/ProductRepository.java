package com.gustavo.managementsystem.Products;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import com.gustavo.managementsystem.Products.ProductModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductRepository {
    
    private ArrayList<ProductModel> products = new ArrayList<>();

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel body){

        products.add(body);
        return body;
    }

    @GetMapping
    public List<ProductModel> readAllProducts(){
        return products;
    }

    @GetMapping("/{id}")
    public ProductModel readProduct(@PathVariable UUID id){

        for(ProductModel p:products){
            if(p.getId().equals(id)){
                return p;
            }
        }

        return null;
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable UUID id, @RequestBody ProductModel body){

        System.err.println(body.getName());

        for(ProductModel p : products){
            if(p.getId().equals(id)){
                p.setName(body.getName());

                return p;
            }
        }

        return null;

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id){

        for( ProductModel p: products){
            if(p.getId().equals(id)){
                products.remove(p);
            }
        }

    }

}
