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
public class PromotionEngineImplTest {
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
    @DisplayName("Calculate price when no Promotions are applied")
    @Order(1)
    public void noPromotionTests() throws EmptyCartException, ProductNotFoundException, InvalidCartException {
        Double finalPrice;
        cart.clearCart();
        cart.setOrderList(List.of('A','B','C'));
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(100,finalPrice);
    }

   @Test
   @DisplayName("Calculate price when single Promotions are applied")
   @Order(2)
    public void singleItemPromoTests() throws ProductNotFoundException, InvalidCartException, EmptyCartException {
       Double finalPrice;
        cart.clearCart();
        cart.setOrderList(List.of('A','B','C','A','A','A','A','B','B','B','B'));
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(370,finalPrice);
    }

    @Test
    @DisplayName("Calculate price when Combo Promotions are applied")
    @Order(3)
    public void comboItemPromoTests() throws ProductNotFoundException, InvalidCartException, EmptyCartException {
        Double finalPrice;
        cart.clearCart();
        cart.setOrderList(List.of('A','B','C','A','A','B','B','B','B','D'));
        finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        Assertions.assertEquals(280,finalPrice);
    }

    @Test
    @DisplayName("Calculate price when multiple promotions are applied and they should be mutually exclusive")
    public void comboItemPromoTests2() throws ProductNotFoundException, InvalidCartException, EmptyCartException {

        cart.clearCart();
        cart.setOrderList(List.of('A','B','C','A','A','B','B','B','B','D','C','D'));
        Double finalPrice = promotionEngine.applyPromotions(promotionList, cart);
        System.out.println(finalPrice);
        Assertions.assertEquals(310,finalPrice);
    }
}
