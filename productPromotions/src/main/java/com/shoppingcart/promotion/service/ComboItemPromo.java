package com.shoppingcart.promotion.service;


import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.model.Inventory;
import com.shoppingcart.promotion.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComboItemPromo implements Promotion {
    private List<Character> skus;
    private Double promoPrice;

    private ComboItemPromo() {
    }

    public ComboItemPromo(List<Character> skus, Double promoPrice) {
        this.skus = skus;
        this.promoPrice = promoPrice;
    }

    public List<Character> getSkus() {
        return skus;
    }

    @Override
    public Double getPromoPrice() {
        return promoPrice;
    }

    @Override
    public boolean isApplicable(Cart cart) throws ProductNotFoundException {

        /*
        To support multiple combo offers. Ex: 2A + 1B,
        count the occurrence of each item to check if promotion can be applicable
         */

        Cart cartCopy = new Cart(cart.getCartItems());

        for (char sku : skus) {
            if (!cartCopy.containsSku(sku)) {
                return true;
            } else {
                cartCopy.removeItemFromCart(Inventory.getItem(sku), 1);
            }
        }

        return false;
    }

    @Override
    public Cart applyPromoOnceAndGiveUpdatedCart(Cart cart) throws ProductNotFoundException {
        if (isApplicable(cart)) {
            throw new IllegalStateException("Check if promo can be applied first.");
        }

        Cart updatedCart = new Cart(cart.getCartItems());

        List<Product> itemsInPromo = new ArrayList<>();
        for (char sku : this.skus) {
            itemsInPromo.add(Inventory.getItem(sku));
        }

        Map<Product, Integer> currCartItems = updatedCart.getCartItems();

        for (Product item : itemsInPromo) {
            if (currCartItems.get(item) == 1) {
                currCartItems.remove(item);
            } else {
                currCartItems.put(item, currCartItems.get(item) - 1);
            }
        }

        return updatedCart;
    }

    @Override
    public Double getPriceReductionWhenAppliedOnce() throws ProductNotFoundException {
        double fixedUnitPrice = 0.0;

        Product item;
        for (char sku : this.skus) {
            item = Inventory.getItem(sku);
            fixedUnitPrice += item.getPrice();
        }

        return fixedUnitPrice - this.promoPrice;
    }
}
