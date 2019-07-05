package com.mdzidko.loyaltylevelsbdd.customer.dto;

public class CustomerDoesntExistsException extends RuntimeException{
    public CustomerDoesntExistsException() {
        super("Customer with given card number doesn't exists");
    }
}
