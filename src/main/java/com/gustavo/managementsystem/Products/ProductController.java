package com.gustavo.managementsystem.Products;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.gustavo.managementsystem.Users.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    @PreAuthorize("@authGuard.isAdmin(authentication)")
    public List<Product> getAllProducts(){

        var listProducts = productService.findAllProducts();

        // var newList = modelMapper.map(listProducts, Product[].class);

        return listProducts;
    }

    @GetMapping()
    @PreAuthorize("@authGuard.isSupplier(authentication)")
    public Optional<Product> getProductsForSupplier(Authentication auth){

        var supplierId = auth.getName();

        return productService.findAllProductsForSupplier(supplierId);
    }

    @PostMapping()
    @PreAuthorize("@authGuard.isSupplier(authentication)")
    public Product postProduct(@Valid @RequestBody ProductCreateDTO payload, Authentication authentication) {

        var userUUID = UUID.fromString(authentication.getName());

        var user = userService.listUserById(userUUID).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        var productFromDb = productService.findProductByName(payload.getName());

        if (productFromDb.isPresent() && productFromDb.get().getSupplier().getId() == user.getId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
        
        Product newProduct = modelMapper.map(payload, Product.class);

        newProduct.setSupplier(user);

        return productService.createProduct(newProduct);
    }

    @GetMapping("/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public Optional<Product> getProduct(@PathVariable Long productId){


        return productService.findProductById(productId);
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
