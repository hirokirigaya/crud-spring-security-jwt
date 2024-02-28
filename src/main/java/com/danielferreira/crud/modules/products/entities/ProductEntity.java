package com.danielferreira.crud.modules.products.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
@Table(name = "products")
@EqualsAndHashCode(of = "id")
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String slug;

    @Column(name = "image", nullable = true)
    private String image;

    private int price_in_cents;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
