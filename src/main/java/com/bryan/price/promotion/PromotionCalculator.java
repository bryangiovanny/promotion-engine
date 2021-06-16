package com.bryan.price.promotion;

import com.bryan.price.model.Cart;

import java.math.BigDecimal;

public interface PromotionCalculator {
    BigDecimal calculatePromotion(Cart cart);
}
