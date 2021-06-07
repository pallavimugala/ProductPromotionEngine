package com.shoppingcart.promotion.model;

import com.shoppingcart.promotion.exception.EmptyCartException;
import com.shoppingcart.promotion.exception.ProductNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * CartItems contains the count of individual products added to the cart.
 * Assumption: Inputs to this engine are list of characters. so the getProductOccurences maps to the cartItems
 */
public class Cart {
    private Map<Product, Integer> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public Cart(Map<Product, Integer> items) {
        this();
        cartItems.putAll(items);
    }

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    public void addItemToCart(Product item, Integer quantity) {

        if (cartItems.containsKey(item)) {
            quantity = quantity + cartItems.get(item);
        }

        cartItems.put(item, quantity);
    }

    public void removeItemFromCart(Product item, Integer quantity) {
        if (cartItems.containsKey(item)) {
            quantity = cartItems.get(item) - quantity;
        }

        if (quantity < 0) {
            throw new IllegalArgumentException();
        }

        if (quantity == 0) {
            cartItems.remove(item);
        }

        cartItems.put(item, quantity);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public boolean containsSku(Character sku) {
        return getItemForSku(sku).isPresent();
    }

    public void setOrderList(List<Character> orderList) throws EmptyCartException, ProductNotFoundException {
        this.cartItems = getProductOccurences(orderList);
    }

    public Optional<Product> getItemForSku(char sku) {
        for (Product item : cartItems.keySet()) {
            if (item.getskuName()==sku) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    private Map<Product, Integer> getProductOccurences(List<Character> orderList) throws EmptyCartException, ProductNotFoundException {
        Map<Product, Integer> skuOccurrenceMap = new HashMap<>();
        if(orderList==null || orderList.size() < 1)
            throw new EmptyCartException("Cart is Empty");
        for (char c : orderList) {
            try {
                skuOccurrenceMap.put(Product.valueOf(String.valueOf(c)), skuOccurrenceMap.getOrDefault(Product.valueOf(String.valueOf(c)), 0) + 1);

            }
            catch(Exception e) {
                throw new ProductNotFoundException("Invalid SKU Type passed" +c);
            }
        }
        return skuOccurrenceMap;
    }
}
