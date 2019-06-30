package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

public class LoyaltyLevelNameDuplicationException extends RuntimeException {
    public LoyaltyLevelNameDuplicationException() {
        super("Can't add loyalty level with same name");
    }
}
