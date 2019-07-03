package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

public class LoyaltyLevelDefaultDuplicationException extends RuntimeException {
    public LoyaltyLevelDefaultDuplicationException() {
        super("Can't add second default loyalty level");
    }
}
