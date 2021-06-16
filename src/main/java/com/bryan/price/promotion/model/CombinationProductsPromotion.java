package com.bryan.price.promotion.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CombinationProductsPromotion {
    private String product1;
    private String product2;
    private BigDecimal price;
}
