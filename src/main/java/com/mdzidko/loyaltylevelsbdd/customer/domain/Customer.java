package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;
import com.mdzidko.loyaltylevelsbdd.customer.dto.LoyaltyLevelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
class Customer {

    @Getter
    private String cardNumber;
    private String loyaltyLevel;
    private double totalBet;

    CustomerDto dto() {
        return CustomerDto.builder()
                    .cardNumber(cardNumber)
                    .loyaltyLevel(loyaltyLevel)
                    .totalBet(totalBet)
                .build();
    }

    Customer bet(double value) {
        totalBet += value;

        return this;
    }

    Customer updateLoyaltyLevel(Set<LoyaltyLevelDto> loyaltyLevelsConfiguration) {

        Optional<LoyaltyLevelDto> llc = loyaltyLevelsConfiguration.stream()
                .filter(ll -> ll.getLowerLevelBound() < this.totalBet)
                .max(Comparator.comparingInt(LoyaltyLevelDto::getLowerLevelBound));

        llc.ifPresent(loyaltyLevelDto -> this.loyaltyLevel = loyaltyLevelDto.getName());

        return this;
    }
}
