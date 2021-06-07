package com.shoppingcart.promotion.model;

/**
 * Product Enumeration has all the SKU's (Stock Keeping Units)
 * These can be fetched from DB to make the enum dynamic.
 *
 */

public enum Product {
    A('A',50),
    B('B',30),
    C('C',20),
    D('D',15);

    private final char skuName;
    private final double price;

    Product(char skuName,double price) {
        this.skuName = skuName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public char getskuName() {return skuName;}

    @Override
    public String toString() {
        return "CartItems{" +
                "skuName=" + skuName +
                ", price=" + price +
                '}';
    }
}
