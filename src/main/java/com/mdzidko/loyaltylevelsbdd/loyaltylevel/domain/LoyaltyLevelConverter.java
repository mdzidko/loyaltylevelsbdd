package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

class LoyaltyLevelConverter {

    LoyaltyLevel convert(LoyaltyLevelDto levelDto) {

        return new LoyaltyLevel(levelDto.getName(),
                                levelDto.getPointsBonusPercentage(),
                                levelDto.getLowerLevelBound());
    }
}
