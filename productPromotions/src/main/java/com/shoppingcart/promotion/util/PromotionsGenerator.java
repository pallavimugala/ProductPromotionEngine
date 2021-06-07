package com.shoppingcart.promotion.util;

import com.shoppingcart.promotion.service.ComboItemPromo;
import com.shoppingcart.promotion.service.Promotion;
import com.shoppingcart.promotion.service.SingleItemPromo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PromotionsGenerator {
    public static List<Promotion> generatePromotions() {
        final List<Promotion> promotions = new ArrayList<>();

        Promotion promo1 = new SingleItemPromo('A', 3, 130.00);
        Promotion promo2 = new SingleItemPromo('B', 2, 45.00);
        Promotion promo3 = new ComboItemPromo(Arrays.asList('C', 'D'), 30.00);

        promotions.addAll(Arrays.asList(promo1, promo2, promo3));

        return promotions;
    }
}
