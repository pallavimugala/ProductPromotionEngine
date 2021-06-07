package com.shoppingcart.promotion.test;

import com.shoppingcart.promotion.engine.PromotionEngine;
import com.shoppingcart.promotion.engine.PromotionEngineImpl;
import com.shoppingcart.promotion.exception.EmptyCartException;
import com.shoppingcart.promotion.exception.InvalidCartException;
import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.service.Promotion;
import com.shoppingcart.promotion.util.InventoryGenerator;
import com.shoppingcart.promotion.util.PromotionsGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CaseStudyExampleTests {
    private static PromotionEngine promotionEngine;
    private static List<Promotion> promotionList;
    private static Cart cart;

    @BeforeAll
    public static void setup() {
        promotionEngine = new PromotionEngineImpl();
        promotionList = PromotionsGenerator.generatePromotions();
        InventoryGenerator.generateInventory();
        cart = new Cart();
    }

    @Test
    @DisplayName("Scenario A")
    @Order(1)
    public void scenarioA() throws EmptyCartException, ProductNotFoundException, InvalidCartException {
        Double finalPrice;
        cart.clearCart();
        cart.setOrderList(List.of('A','B','C'));
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(100,finalPrice);
    }

    @ParameterizedTest
    @MethodSource("cartItemsList")
    @DisplayName("Scenario B")
    @Order(2)
    public void scenarioB(List<Character> orderList) throws EmptyCartException, ProductNotFoundException, InvalidCartException {
        Double finalPrice;
        cart.clearCart();
        cart.setOrderList(orderList);
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(370,finalPrice);
    }

    private static Stream<Arguments> cartItemsList() {
        return Stream.of(
                Arguments.of(List.of('A','B','C','A','A','A','A','B','B','B','B'))
        );
    }

    /**
     * 130 + 120 + 30 == 280
     * In this example, all the promotions are applied as there is only promotion on an SKU
     */
    @Test
    @DisplayName("Scenario C")
    @Order(3)
    public void scenarioC() throws EmptyCartException, ProductNotFoundException, InvalidCartException {
        Double finalPrice;
        cart.clearCart();
        cart.setOrderList(List.of('A','B','C','A','A','B','B','B','B','D'));
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(280,finalPrice);
    }


}
