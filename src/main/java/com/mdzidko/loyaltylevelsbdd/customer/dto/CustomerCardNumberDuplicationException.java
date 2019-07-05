package com.mdzidko.loyaltylevelsbdd.customer.dto;

public class CustomerCardNumberDuplicationException extends RuntimeException {
    public CustomerCardNumberDuplicationException() {
        super("Customer with given card number already exists");
    }
}
