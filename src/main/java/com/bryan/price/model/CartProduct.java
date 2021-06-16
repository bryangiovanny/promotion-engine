package com.bryan.price.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProduct {
    private Product product;
    private Integer quantity;
}
