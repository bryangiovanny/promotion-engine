package com.bryan.price.promotion.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CombinationProductsPromotionCalculator {
    private List<String> productIds;
    private BigDecimal price;
}
