package com.shoppingcart.promotion.service;

import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;

public interface Promotion {
    boolean isApplicable(Cart cart) throws ProductNotFoundException;

    Double getPriceReductionWhenAppliedOnce() throws ProductNotFoundException;

    Cart applyPromoOnceAndGiveUpdatedCart(Cart cart) throws ProductNotFoundException;
}
