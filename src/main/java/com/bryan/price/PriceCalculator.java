package com.bryan.price;

import com.bryan.price.model.Cart;
import com.bryan.price.promotion.PromotionConfiguration;

import java.math.BigDecimal;

public class PriceCalculator {

    public BigDecimal calculatePrice(Cart inputCart) {
        BigDecimal promotionPrice = PromotionConfiguration.getPromotionCalculators().stream()
                .map(promotionCalculator -> promotionCalculator.calculatePromotion(inputCart))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal priceOfItemWithoutPromo = inputCart.getCartProductList().stream()
                .map(cartProduct -> cartProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new IllegalArgumentException("Cart is not valid"));
        return promotionPrice.add(priceOfItemWithoutPromo);
    }
}
