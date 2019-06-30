package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

public class LoyaltyLevelDoesntExistException extends RuntimeException{
    public LoyaltyLevelDoesntExistException() {
        super("Loyalty level with given id doesn't exist");
    }
}
