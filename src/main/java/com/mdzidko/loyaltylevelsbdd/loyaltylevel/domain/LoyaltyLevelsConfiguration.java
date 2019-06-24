package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

public class LoyaltyLevelsConfiguration {

    public LoyaltyLevelsFacade loyaltyLevelsFacade(){

        return new LoyaltyLevelsFacade(new InMemoryLoyaltyLevelsRepository(), new LoyaltyLevelConverter());
    }
}
