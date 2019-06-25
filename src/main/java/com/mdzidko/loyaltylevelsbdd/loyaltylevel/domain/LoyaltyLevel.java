package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
class LoyaltyLevel {

    private UUID uuid;
    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

    public LoyaltyLevel(String name, double pointsBonusPercentage, int lowerLevelBound) {

        this.uuid = UUID.randomUUID();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoyaltyLevel that = (LoyaltyLevel) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}
