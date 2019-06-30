package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
class LoyaltyLevel {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private long id;

    @Getter
    private String name;
    private double pointsBonusPercentage;
    private int lowerLevelBound;

    LoyaltyLevel(String name, double pointsBonusPercentage, int lowerLevelBound) {

        this.name = name;
        this.pointsBonusPercentage = pointsBonusPercentage;
        this.lowerLevelBound = lowerLevelBound;
    }

    LoyaltyLevelDto dto() {
        return LoyaltyLevelDto.builder()
                    .id(id)
                    .name(name)
                    .pointsBonusPercentage(pointsBonusPercentage)
                    .lowerLevelBound(lowerLevelBound)
                .build();
    }


}
