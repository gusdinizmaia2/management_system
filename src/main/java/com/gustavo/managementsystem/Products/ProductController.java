package com.gustavo.managementsystem.Products;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@PreAuthorize("")
public class ProductController {
    
    private ProductService productService;

    @PreAuthorize("@authGuard.isAdmin(authentication)")
    @GetMapping("/all")
    public List<Product> getAllProducts(){

        return productService.findAllProducts();
    }

    @GetMapping()
    @PreAuthorize("@authGuard.isSupplier(authentication)")
    public Optional<Product> getProductsForSupplier(Authentication auth){

        var supplierId = auth.getName();

        return productService.findAllProductsForSupplier(supplierId);
    }

    @PostMapping()
    @PreAuthorize("@authGuard.isSupplier(authentication)")
    public List<Product> postProduct(){

        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public Optional<Product> getProduct(@PathVariable Long productId){


        return productService.findProduct(productId);
    }

    @PatchMapping("/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")

    public Optional<Product> patchProduct(@PathVariable Long productId,@Valid @RequestBody Map<String,String> payload){

        return productService.updateProduct(productId, payload);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")

    public void deleteProduct(@PathVariable Long productId){

        productService.removeProduct(productId);
    }

}
