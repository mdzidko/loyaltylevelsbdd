package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoyaltyLevelDto {

    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

}
