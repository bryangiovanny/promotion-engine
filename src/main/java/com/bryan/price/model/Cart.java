package com.bryan.price.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cart {
    private List<CartProduct> cartProductList;
}
