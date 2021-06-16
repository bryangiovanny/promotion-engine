package com.bryan.price.promotion;

import com.bryan.price.model.Cart;

import java.math.BigDecimal;

/**
 * This will return an applied promo price and reduce quantity of already applied promo.
 */
public interface PromotionCalculator {
    BigDecimal calculatePromotion(Cart cart);
}
