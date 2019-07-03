package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
