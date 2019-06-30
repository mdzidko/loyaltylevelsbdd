package com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoyaltyLevelDto {

    private long id;
    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

}
