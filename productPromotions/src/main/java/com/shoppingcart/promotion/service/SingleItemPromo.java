package com.shoppingcart.promotion.service;

import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.model.Inventory;
import com.shoppingcart.promotion.model.Product;

import java.util.Optional;

public class SingleItemPromo implements Promotion {
    private Character sku;
    private Integer quantity;
    private Double promoPrice;

    private SingleItemPromo() {
    }

    public SingleItemPromo(Character sku, Integer quantity, Double promoPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.promoPrice = promoPrice;
    }

    public Character getSku() {
        return sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public Double getPromoPrice() {
        return promoPrice;
    }

    @Override
    public boolean isApplicable(Cart cart) {

        Optional<Product> item = cart.getItemForSku(this.sku);

        if (item.isPresent() && cart.getCartItems().get(item.get()) >= this.quantity) {
            return true;
        }

        return false;
    }

    @Override
    public Cart applyPromoOnceAndGiveUpdatedCart(Cart cart) throws ProductNotFoundException {

        if (this.isApplicable(cart)) {
            throw new IllegalStateException("Check if promo can be applied first.");
        }

        Cart updatedCart = new Cart(cart.getCartItems());
        Product item = Inventory.getItem(this.sku);

        Integer value = updatedCart.getCartItems().get(item);

        if (value - quantity == 0) {
            updatedCart.getCartItems().remove(item);
        } else {
            updatedCart.getCartItems().put(item, value - quantity);
        }

        return updatedCart;
    }

    @Override
    public Double getPriceReductionWhenAppliedOnce() throws ProductNotFoundException {
        Product item = Inventory.getItem(this.sku);
        return (item.getPrice() * this.quantity) - this.promoPrice;
    }
}
