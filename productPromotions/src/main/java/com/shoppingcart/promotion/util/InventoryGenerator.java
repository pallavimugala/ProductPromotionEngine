package com.shoppingcart.promotion.util;

import com.shoppingcart.promotion.model.Inventory;
import com.shoppingcart.promotion.model.Product;

public class InventoryGenerator {

    public static Inventory generateInventory() {
        Inventory inventory = new Inventory();
        inventory.addItem(Product.A);
        inventory.addItem(Product.B);
        inventory.addItem(Product.C);
        inventory.addItem(Product.D);
        return inventory;
    }
}
