package com.bryan.price.promotion;

import com.bryan.price.model.Cart;
import com.bryan.price.promotion.model.MultipleProductsPromotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MultipleProductsPromotionCalculator implements PromotionCalculator {

    private final List<MultipleProductsPromotion> multipleProductsPromotionList = new ArrayList<>();

    public MultipleProductsPromotionCalculator() {
        // Typically configured in database
        multipleProductsPromotionList.add(MultipleProductsPromotion
                .builder().id("A").quantity(3).price(BigDecimal.valueOf(130))
                .build());
        multipleProductsPromotionList.add(MultipleProductsPromotion
                .builder().id("B").quantity(2).price(BigDecimal.valueOf(45))
                .build());
    }

    @Override
    public BigDecimal calculatePromotion(Cart cart) {
        return multipleProductsPromotionList.stream()
                .map(multipleProductsPromotion ->
                        cart.getCartProductList().stream()
                                .filter(cartProduct -> cartProduct.getProduct().getId().equals(multipleProductsPromotion.getId()) &&
                                        cartProduct.getQuantity() >= multipleProductsPromotion.getQuantity())
                                .map(cartProduct -> {
                                    int numOfPromo = cartProduct.getQuantity() / multipleProductsPromotion.getQuantity();
                                    int quantityLeft = cartProduct.getQuantity() % multipleProductsPromotion.getQuantity();
                                    cartProduct.setQuantity(quantityLeft);
                                    return multipleProductsPromotion.getPrice().multiply(BigDecimal.valueOf(numOfPromo));
                                }).reduce(BigDecimal.ZERO, BigDecimal::add)
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
