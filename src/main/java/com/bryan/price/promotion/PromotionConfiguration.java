package com.bryan.price.promotion;

import java.util.List;

public class PromotionConfiguration {
    public static List<PromotionCalculator> getPromotionCalculators() {
        return List.of(new MultipleProductsPromotionCalculator(), new CombinationProductPromotionCalculator());
    }
}
