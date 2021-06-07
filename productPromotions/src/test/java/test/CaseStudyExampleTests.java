package test;

import com.shoppingcart.promotion.engine.PromotionEngine;
import com.shoppingcart.promotion.engine.PromotionEngineImpl;
import com.shoppingcart.promotion.exception.EmptyCartException;
import com.shoppingcart.promotion.exception.InvalidCartException;
import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.model.Inventory;
import com.shoppingcart.promotion.service.Promotion;
import com.shoppingcart.promotion.util.InventoryGenerator;
import com.shoppingcart.promotion.util.PromotionsGenerator;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CaseStudyExampleTests {
    private static PromotionEngine promotionEngine;
    private static List<Promotion> promotionList;
    private static Inventory inventory;
    private static Cart cart;

    @BeforeAll
    public static void setup() throws EmptyCartException, ProductNotFoundException {
        promotionEngine = new PromotionEngineImpl();
        promotionList = PromotionsGenerator.generatePromotions();
        inventory = InventoryGenerator.generateInventory();
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

    @Test
    @DisplayName("Scenario B")
    @Order(2)
    public void scenarioB() throws EmptyCartException, ProductNotFoundException, InvalidCartException {
        Double finalPrice;
        cart.clearCart();
        cart.setOrderList(List.of('A','B','C','A','A','A','A','B','B','B','B'));
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(370,finalPrice);
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
