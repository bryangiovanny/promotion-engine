package com.bryan.price.promotion;

import com.bryan.price.model.Cart;

import java.math.BigDecimal;

public class MultipleProductsPromotionCalculator implements PromotionCalculator {

    @Override
    public BigDecimal calculatePromotion(Cart cart) {
        return BigDecimal.ZERO;
    }

}
