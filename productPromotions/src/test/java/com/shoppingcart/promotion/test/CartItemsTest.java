package com.shoppingcart.promotion.test;

import com.shoppingcart.promotion.exception.EmptyCartException;
import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.model.Product;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartItemsTest {

    private static Cart cart;

    @BeforeAll
    static void initializeCartItems() throws Exception {
        cart= new Cart();
        cart.setOrderList(List.of('A','B','C','D','A','A'));
    }

    @Test
    @DisplayName("Check if the cart items are valid. ")
    @Order(1)
    public void checkValidCartItems() {
        Cart dummyCart = new Cart();
        Assertions.assertDoesNotThrow(()->dummyCart.setOrderList(List.of('A','B','C','D','A','A')), String.valueOf(ProductNotFoundException.class));
    }

    @Test
    @DisplayName("Check if exception is thrown if invalid CART item is passed. ")
    @Order(2)
    public void checkInValidCartItems() {
        Assertions.assertThrows(IllegalArgumentException.class,()->cart.addItemToCart(Product.valueOf("N"),2));
    }

    @Test
    @DisplayName("Check if the cart is empty")
    @Order(3)
    public void isCartEmpty() {
        Cart cart = new Cart();
        Assertions.assertThrows(EmptyCartException.class,()->cart.setOrderList(null));
    }

    @Test
    @DisplayName("Count SKU occurences")
    @Order(4)
    public void countSkuOccurences() {
        Map<Product,Integer> skuOccurenceMap = cart.getCartItems();
        Assertions.assertNotNull(skuOccurenceMap);
        Assertions.assertEquals(3,skuOccurenceMap.get(Product.A));
        Assertions.assertEquals(1,skuOccurenceMap.get(Product.B));
        Assertions.assertEquals(1,skuOccurenceMap.get(Product.C));
        Assertions.assertEquals(1,skuOccurenceMap.get(Product.D));
    }
}
