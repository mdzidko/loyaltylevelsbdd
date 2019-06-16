package com.mdzidko.loyaltylevelsbdd.loyaltylevels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoyaltyLevelDto {
    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

}
