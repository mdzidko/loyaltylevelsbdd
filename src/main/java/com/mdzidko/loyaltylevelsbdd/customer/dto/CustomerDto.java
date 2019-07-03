package com.mdzidko.loyaltylevelsbdd.customer.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerDto {

    private String cardNumber;
    private String loyaltyLevel;
    private double totalBet;

}
