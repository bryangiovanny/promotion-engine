package com.bryan.price;

import com.bryan.price.model.Cart;
import com.bryan.price.promotion.CombinationProductPromotionCalculator;
import com.bryan.price.promotion.MultipleProductsPromotionCalculator;

import java.math.BigDecimal;

public class PriceCalculator {

    public BigDecimal calculatePrice(Cart inputCart) {
        BigDecimal totalPrice = new MultipleProductsPromotionCalculator().calculatePromotion(inputCart);
        totalPrice = totalPrice.add(new CombinationProductPromotionCalculator().calculatePromotion(inputCart));
        BigDecimal priceOfItemWithoutPromo = inputCart.getCartProductList().stream()
                .map(cartProduct -> cartProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new IllegalArgumentException("Cart is not valid"));
        return totalPrice.add(priceOfItemWithoutPromo);
    }
}
