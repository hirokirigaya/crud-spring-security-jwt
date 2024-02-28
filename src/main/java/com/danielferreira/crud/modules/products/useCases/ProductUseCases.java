package com.danielferreira.crud.modules.products.useCases;

import com.danielferreira.crud.modules.products.dto.RequestProductDTO;
import com.danielferreira.crud.modules.products.entities.ProductEntity;
import com.danielferreira.crud.modules.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductUseCases {

    @Autowired
    ProductRepository repository;

    public List<ProductEntity> findAll () {
        return repository.findAll();
    }

    public ProductEntity findById(String id) {
        var product = repository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        return product.get();
    }

    public ProductEntity create(RequestProductDTO data) {
        var product = ProductEntity.builder()
                .name(data.name())
                .slug(data.slug())
                .price_in_cents(data.price_in_cents())
                .image(data.image())
                .build();

        repository.save(product);
        return product;
    }
}
