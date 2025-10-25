package com.gustavo.managementsystem.InventoryMovements;
import java.util.List;
import java.util.Optional;
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

    public List<InventoryMovement> findMovementsBySupplier(long productId, int quantity, String supplierId){

        var supplierUUID = UUID.fromString(supplierId);

        return inventoryMovementRepository.findAll();
    }

    public List<InventoryMovement> findAllMovements(long productId, UUID supplierId){

        var productString = Long.toString(productId);

        if(productString==null && supplierId==null ){
             return inventoryMovementRepository.findAllInventoryMovementsByOwnerUser_Id(supplierId);
        }
        else if (supplierId!=null ){
            return inventoryMovementRepository.findAllInventoryMovementsByOwnerUser_IdAndProduct_Id(productId, supplierId);
        }
        else if(productString != null){
            return inventoryMovementRepository.findAllInventoryMovementsByProduct_Id(productId);
        }

        return inventoryMovementRepository.findAll();

    }

    public InventoryMovement findMovementById(long movementId){
        var foundMovement = inventoryMovementRepository.findById(movementId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movement not found"));

        return foundMovement;
    }

    public InventoryMovement updateCountInventory(InventoryMovementCreateDTO payload,long productId, String userId){

        var userUUID = UUID.fromString(userId);

        var product = productRepository.findById(productId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        var user = userRepository.findById(userUUID)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        int productQuantity = product.getQuantity();
        int movementQuantity = payload.getQuantity();

        switch (payload.getType()) {
            case ENTRY:

                var entryQuantity = productQuantity + movementQuantity;

                product.setQuantity(entryQuantity);

                break;
        
            case OUTPUT:

                if(productQuantity < movementQuantity){
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                }   

                var outputQuantity = productQuantity - movementQuantity;

                product.setQuantity(outputQuantity);

                break;
        }
        
        InventoryMovement newMovement = modelMapper.map(payload, InventoryMovement.class);
        
        newMovement.setProduct(product);
        newMovement.setOwner_user(user);
        
        productRepository.save(product);

        return inventoryMovementRepository.save(newMovement);
    }

}