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

class MultipleProductsPromotionCalculatorTest {

    MultipleProductsPromotionCalculator multipleProductsPromotion = new MultipleProductsPromotionCalculator();

    private static Stream<Arguments> provideCartInputAndExpectation() {
        return Stream.of(
                Arguments.of(
                        "Given: A=3 B=1;When : Calculate Total Price; Then: Result=130, A remaining 2 B remaining 1",
                        new Cart(List.of(
                                new CartProduct(new Product("A", BigDecimal.valueOf(50)), 5),
                                new CartProduct(new Product("B", BigDecimal.valueOf(30)), 1)
                        )),
                        BigDecimal.valueOf(130),
                        Map.of("A", 2, "B", 1)
                ),
                Arguments.of(
                        "Given: A=1 B=5;When : Calculate Total Price; Then: Result=90, A remaining 1 B remaining 1",
                        new Cart(List.of(
                                new CartProduct(new Product("A", BigDecimal.valueOf(50)), 1),
                                new CartProduct(new Product("B", BigDecimal.valueOf(30)), 5)
                        )),
                        BigDecimal.valueOf(90),
                        Map.of("A", 1, "B", 1)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCartInputAndExpectation")
    void calculateTest(String scenario, Cart cart, BigDecimal expectedTotal, Map<String, Integer> productQuantityRemainingMap) {
        assertEquals(expectedTotal, multipleProductsPromotion.calculatePromotion(cart));
        cart.getCartProductList().forEach(cartProduct ->
                assertEquals(productQuantityRemainingMap.get(cartProduct.getProduct().getId()), cartProduct.getQuantity())
        );
    }
}