package com.bryan.price.promotion;

import com.bryan.price.model.Cart;
import com.bryan.price.model.CartProduct;
import com.bryan.price.model.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationProductPromotionCalculatorTest {

    private final CombinationProductPromotionCalculator combinationProductPromotionCalculator = new CombinationProductPromotionCalculator();

    private static Stream<Arguments> provideCartInputAndExpectation() {
        return Stream.of(
                Arguments.of(
                        "Given: A=1 C=1 D=1;When : Calculate Total Price; Then: Result=30, A remaining 1 C remaining 0 D remaining 0",
                        new Cart(List.of(
                                new CartProduct(new Product("A", BigDecimal.valueOf(50)), 1),
                                new CartProduct(new Product("C", BigDecimal.valueOf(30)), 1),
                                new CartProduct(new Product("D", BigDecimal.valueOf(15)), 1)
                        )),
                        BigDecimal.valueOf(30),
                        Map.of("A", 1, "C", 0, "D", 0)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCartInputAndExpectation")
    void calculateTest(String scenario, Cart cart, BigDecimal expectedTotal, Map<String, Integer> productQuantityRemainingMap) {
        assertEquals(expectedTotal, combinationProductPromotionCalculator.calculatePromotion(cart));
        cart.getCartProductList().forEach(cartProduct ->
                assertEquals(productQuantityRemainingMap.get(cartProduct.getProduct().getId()), cartProduct.getQuantity())
        );
    }
}