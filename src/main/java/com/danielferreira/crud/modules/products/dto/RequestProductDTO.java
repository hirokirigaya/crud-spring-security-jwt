package com.danielferreira.crud.modules.products.dto;

public record RequestProductDTO(String name, String slug, Integer price_in_cents, String image) {
}
