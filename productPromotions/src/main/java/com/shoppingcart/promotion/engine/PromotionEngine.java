package com.shoppingcart.promotion.engine;

import com.shoppingcart.promotion.exception.InvalidCartException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.service.Promotion;

import java.util.List;

public interface PromotionEngine {
    Double applyPromotions(List<Promotion> currentPromotions, Cart cart) throws InvalidCartException;
}
