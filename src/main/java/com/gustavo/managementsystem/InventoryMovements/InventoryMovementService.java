package com.gustavo.managementsystem.InventoryMovements;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.managementsystem.Products.ProductRepository;
import com.gustavo.managementsystem.Users.UserRepository;

@Service
public class InventoryMovementService{

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryMovement> findAllInventoryMovements(){
        return inventoryMovementRepository.findAll();
    }

    public InventoryMovement updateCountInventory(InventoryMovementCreateDTO payload,long productId, String userId){

        var userUUID = UUID.fromString(userId);

        var product = productRepository.findById(productId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));

        var user = userRepository.findById(userUUID)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        int productQuantity = product.getQuantity();
        int movementQuantity = payload.getQuantity();

        switch (payload.getType()) {
            case ENTRY:

                product.setQuantity( productQuantity + movementQuantity);

                break;
        
            case OUTPUT:

                if(productQuantity < movementQuantity){
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                }

                product.setQuantity(productQuantity - movementQuantity);

                break;
        }

        productRepository.save(product);

        InventoryMovement newMovement = modelMapper.map(payload, InventoryMovement.class);

        newMovement.setProduct(product);
        newMovement.setOwner_user(user);

        return inventoryMovementRepository.save(newMovement);
    }

}