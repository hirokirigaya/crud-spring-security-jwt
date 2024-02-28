package com.danielferreira.crud.modules.products.controllers;

import com.danielferreira.crud.modules.products.dto.RequestProductDTO;
import com.danielferreira.crud.modules.products.entities.ProductEntity;
import com.danielferreira.crud.modules.products.repositories.ProductRepository;
import com.danielferreira.crud.modules.products.useCases.ProductUseCases;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductUseCases productUseCases;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> fetchAll() {
        return ResponseEntity.ok(productUseCases.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable String id) {
        var product = productUseCases.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public  ResponseEntity<ProductEntity> createProduct(@RequestBody @Valid RequestProductDTO data) {
        var product = productUseCases.create(data);
        return ResponseEntity.ok(product);
    }
}
