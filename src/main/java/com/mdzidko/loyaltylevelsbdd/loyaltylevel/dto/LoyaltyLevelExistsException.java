package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

public class LoyaltyLevelExistsException extends RuntimeException {
    public LoyaltyLevelExistsException() {
        super("Can't add loyalty level with same name");
    }
}
