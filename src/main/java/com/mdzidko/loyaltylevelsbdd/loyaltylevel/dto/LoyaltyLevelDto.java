package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoyaltyLevelDto {

    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

}
