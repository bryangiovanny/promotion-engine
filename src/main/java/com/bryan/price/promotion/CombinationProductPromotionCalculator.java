package com.bryan.price.promotion;

import com.bryan.price.model.Cart;
import com.bryan.price.model.CartProduct;
import com.bryan.price.promotion.model.CombinationProductsPromotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombinationProductPromotionCalculator implements PromotionCalculator {

    List<CombinationProductsPromotion> combinationProductPromotions = new ArrayList<>();

    public CombinationProductPromotionCalculator() {
        combinationProductPromotions
                .add(CombinationProductsPromotion.builder()
                        .product1("C")
                        .product2("D")
                        .price(BigDecimal.valueOf(30))
                        .build());
    }

    @Override
    public BigDecimal calculatePromotion(Cart cart) {
        return combinationProductPromotions.stream().map(combinationProductsPromotion -> {
            Optional<CartProduct> product1 = cart.getCartProductList().stream()
                    .filter(cartProduct -> cartProduct.getProduct().getId().equals(combinationProductsPromotion.getProduct1()))
                    .findFirst();

            Optional<CartProduct> product2 = cart.getCartProductList().stream()
                    .filter(cartProduct -> cartProduct.getProduct().getId().equals(combinationProductsPromotion.getProduct2()))
                    .findFirst();

            BigDecimal promotion = BigDecimal.ZERO;

            if (product1.isPresent() && product2.isPresent()) {
                product1.get().setQuantity(product1.get().getQuantity() - 1);
                product2.get().setQuantity(product2.get().getQuantity() - 1);
                promotion = promotion.add(combinationProductsPromotion.getPrice());
            }
            return promotion;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
