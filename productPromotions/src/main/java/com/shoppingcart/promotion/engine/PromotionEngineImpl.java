package com.shoppingcart.promotion.engine;

import com.shoppingcart.promotion.exception.InvalidCartException;
import com.shoppingcart.promotion.exception.ProductNotFoundException;
import com.shoppingcart.promotion.model.Cart;
import com.shoppingcart.promotion.model.Product;
import com.shoppingcart.promotion.service.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PromotionEngineImpl implements PromotionEngine {

    @Override
    public Double applyPromotions(List<Promotion> currentPromotions, Cart cart) throws InvalidCartException {
        double price = 0.0;
        Map<Product, Integer> cartItems = cart.getCartItems();

        // Calculate initial fixed unit prices for all items in cart
        for (Product item : cartItems.keySet()) {
            price += (item.getPrice() * cartItems.get(item));
        }

        try {
            return applyBestPromotion(currentPromotions, cart, price);
        } catch (ProductNotFoundException e) {
            System.out.println();
            throw new InvalidCartException("Cart INVALID!!");
        }
    }

    /*
    Applies the best promotion which can be applied recursively and gives the final price
     */
    private Double applyBestPromotion(List<Promotion> promotions, Cart cart, Double currPrice) throws ProductNotFoundException {

        List<Promotion> eligiblePromotions = new ArrayList<>();

        // Check all promotions which can be applied on the current cart
        for (Promotion promo : promotions) {
            if (promo.isApplicable(cart)) {
                eligiblePromotions.add(promo);
            }
        }

        // No promotions left. We applied everything possible.
        if (eligiblePromotions.isEmpty()) {
            return currPrice;
        }

        /*
        For each promotion that can be applied, Find the promotion that reduces the
        final checkout price the most.

        Once found, update the cart to remove items for which promo is applied.
         */
        double priceReduction = 0;
        Promotion promoToBeApplied = null;
        for (Promotion promo : eligiblePromotions) {
            double priceReductionForThisOffer = promo.getPriceReductionWhenAppliedOnce();
            if (priceReductionForThisOffer > priceReduction) {
                priceReduction = priceReductionForThisOffer;
                promoToBeApplied = promo;
            }
        }

        Cart updatedCart = promoToBeApplied != null ? promoToBeApplied.applyPromoOnceAndGiveUpdatedCart(cart) : null;
        currPrice = currPrice - priceReduction;

        /*
        Now that the best offer is applied and the final price reduced,
        Check if we can apply more promotions on the items left in the cart.

        As this happens recursively, We will obtain the best price at the end
         */
        return applyBestPromotion(promotions, updatedCart, currPrice);
    }
}