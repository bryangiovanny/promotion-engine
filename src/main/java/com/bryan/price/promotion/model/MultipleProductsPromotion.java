package com.bryan.price.promotion.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MultipleProductsPromotion {
    private String id;
    private Integer quantity;
    private BigDecimal price;
}
