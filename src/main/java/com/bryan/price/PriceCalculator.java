package com.bryan.price;

import com.bryan.price.model.Cart;

import java.math.BigDecimal;

public class PriceCalculator {

    public BigDecimal calculatePrice(Cart cart) {
        return cart.getCartProductList().stream()
                .map(cartProduct -> cartProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new IllegalArgumentException("Cart is not valid"));
    }
}
