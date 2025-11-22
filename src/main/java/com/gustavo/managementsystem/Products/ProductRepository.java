package com.gustavo.managementsystem.Products;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>{
    Optional<Product> findById(long id);
    Optional<Product> findByName(String name);
    Optional<Product> findAllBySupplier_Id(UUID supplierId);
}
