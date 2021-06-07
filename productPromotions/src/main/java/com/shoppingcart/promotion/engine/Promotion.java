package com.shoppingcart.promotion.engine;

import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;

public interface Promotion {
    boolean isApplicable(Cart cart) throws ProductNotFoundException;

    Double getPromoPrice();

    Double getPriceReductionWhenAppliedOnce() throws ProductNotFoundException;

    Cart applyPromoOnceAndGiveUpdatedCart(Cart cart) throws ProductNotFoundException;
}
