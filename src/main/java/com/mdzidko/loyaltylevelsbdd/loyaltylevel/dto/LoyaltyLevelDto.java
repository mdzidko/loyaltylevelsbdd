package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class LoyaltyLevelDto {

    private String uuid;
    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

}
