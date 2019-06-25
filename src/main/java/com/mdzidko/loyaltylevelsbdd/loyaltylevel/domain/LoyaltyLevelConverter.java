package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

import java.util.UUID;

class LoyaltyLevelConverter {

    LoyaltyLevel convert(LoyaltyLevelDto levelDto) {

        UUID uuid = null;
        String dtoUuid = levelDto.getUuid();

        if(dtoUuid != null && !dtoUuid.isEmpty())
            uuid = UUID.fromString(dtoUuid);

        return new LoyaltyLevel(uuid,
                                levelDto.getName(),
                                levelDto.getPointsBonusPercentage(),
                                levelDto.getLowerLevelBound());
    }
}
