package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import lombok.Getter;

import java.util.Objects;

@Getter
class LoyaltyLevel {

    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

    public LoyaltyLevel(String name, double pointsBonusPercentage, int lowerLevelBound) {

        this.name = name;
        this.pointsBonusPercentage = pointsBonusPercentage;
        this.lowerLevelBound = lowerLevelBound;
    }

    LoyaltyLevelDto dto() {
        return LoyaltyLevelDto.builder()
                    .name(name)
                    .pointsBonusPercentage(pointsBonusPercentage)
                    .lowerLevelBound(lowerLevelBound)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoyaltyLevel)) return false;
        LoyaltyLevel that = (LoyaltyLevel) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
