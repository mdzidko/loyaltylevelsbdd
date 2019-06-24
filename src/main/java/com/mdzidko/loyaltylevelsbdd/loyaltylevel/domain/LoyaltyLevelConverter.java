package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

class LoyaltyLevelConverter {

    LoyaltyLevel convert(LoyaltyLevelDto levelDto) {

        return LoyaltyLevel.builder()
                    .name(levelDto.getName())
                    .pointsBonusPercentage(levelDto.getPointsBonusPercentage())
                    .lowerLevelBound(levelDto.getLowerLevelBound())
                .build();
    }
}
