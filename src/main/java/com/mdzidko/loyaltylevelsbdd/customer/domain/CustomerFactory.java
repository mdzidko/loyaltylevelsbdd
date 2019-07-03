package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.LoyaltyLevelDto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class CustomerFactory {

    Customer create(String cardNumber, Set<LoyaltyLevelDto> loyaltyLevelsConfiguration) {

        String defaultLoyaltyLevel = loyaltyLevelsConfiguration.stream()
                                        .filter(LoyaltyLevelDto::isDefault)
                                        .findFirst().get().getName();

        return new Customer(cardNumber, defaultLoyaltyLevel, 0.0);
    }
}
