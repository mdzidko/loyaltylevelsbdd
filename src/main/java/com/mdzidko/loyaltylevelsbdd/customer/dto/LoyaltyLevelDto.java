package com.mdzidko.loyaltylevelsbdd.customer.dto;

import lombok.Data;

@Data
public class LoyaltyLevelDto{

    private String name;
    private int lowerLevelBound;
    private boolean isDefault;
}
