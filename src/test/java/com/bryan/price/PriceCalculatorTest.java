package com.bryan.price;

import com.bryan.price.model.Cart;
import com.bryan.price.model.CartProduct;
import com.bryan.price.model.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator = new PriceCalculator();

    private static Stream<Arguments> provideCartInputAndExpectation() {
        return Stream.of(
                Arguments.of(
                        "Given: A=1, B=1, C=1 ;When : Calculate Total Price; Then: Result=100",
                        new Cart(List.of(
                                new CartProduct(new Product("A", BigDecimal.valueOf(50)), 1),
                                new CartProduct(new Product("B", BigDecimal.valueOf(30)), 1),
                                new CartProduct(new Product("C", BigDecimal.valueOf(20)), 1))),
                        BigDecimal.valueOf(100)
                ),
                Arguments.of(
                        "Given: A=5, B=5, C=1 ;When : Calculate Total Price; Then: Result=370",
                        new Cart(List.of(
                                new CartProduct(new Product("A", BigDecimal.valueOf(50)), 5),
                                new CartProduct(new Product("B", BigDecimal.valueOf(30)), 5),
                                new CartProduct(new Product("C", BigDecimal.valueOf(20)), 1))),
                        BigDecimal.valueOf(370)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCartInputAndExpectation")
    void calculateTest(String scenario, Cart cart, BigDecimal expectedTotal) {
        assertEquals(expectedTotal, priceCalculator.calculatePrice(cart));
    }
}