package com.shoppingcart.promotion.model;

import com.shoppingcart.promotion.exception.ProductNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Inventory will be the list of products that are defined within the Product object.
 *  Created this model so that it can be verified against invalid SKU sent in the Cart
 */
public class Inventory {
    private static Set<Product> products;

    public Inventory() {
        products = new HashSet<>();
    }

    public Set getItems() {
        return products;
    }

    public void addItem(Product product) {
        products.add(product);
    }

    public static Product getItem(char skuId) throws ProductNotFoundException {
        for (Product item : products) {
            if (item.getskuName()==skuId) {
                return item;
            }
        }
        throw new ProductNotFoundException("Product "+skuId+" Not Found!!");
    }

}
