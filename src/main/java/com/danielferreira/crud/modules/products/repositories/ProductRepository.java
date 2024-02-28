package com.danielferreira.crud.modules.products.repositories;

import com.danielferreira.crud.modules.products.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
