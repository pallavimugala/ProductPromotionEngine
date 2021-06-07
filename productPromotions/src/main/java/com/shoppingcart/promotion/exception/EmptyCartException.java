package com.shoppingcart.promotion.exception;

public class EmptyCartException extends Exception {

    private static final long serialVersionUID = 1L;
    String exception;

    public EmptyCartException(String exception) {
        this.exception = exception;
    }

    public String toString() {
        return ("CartIsEmptyException Occurred: " + exception);
    }
}
