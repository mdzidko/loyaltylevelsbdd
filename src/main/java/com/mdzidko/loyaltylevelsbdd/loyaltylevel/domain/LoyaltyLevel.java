package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
class LoyaltyLevel {

    private UUID uuid;
    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

    public LoyaltyLevel(UUID uuid, String name, double pointsBonusPercentage, int lowerLevelBound) {

        if(uuid == null)
            this.uuid = UUID.randomUUID();
        else
            this.uuid = uuid;

        this.name = name;
        this.pointsBonusPercentage = pointsBonusPercentage;
        this.lowerLevelBound = lowerLevelBound;
    }

    LoyaltyLevelDto dto() {
        return LoyaltyLevelDto.builder()
                    .uuid(uuid.toString())
                    .name(name)
                    .pointsBonusPercentage(pointsBonusPercentage)
                    .lowerLevelBound(lowerLevelBound)
                .build();
    }

}
