package com.bryan.price.promotion.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MultipleProductsPromotion {
    private String id;
    private Integer quantity;
    private BigDecimal price;
}
