package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

import java.util.List;
import java.util.UUID;

interface LoyaltyLevelsRepository {

    LoyaltyLevel save(LoyaltyLevel level);

    List<LoyaltyLevel> findAll();
    LoyaltyLevelDto findByUuid(UUID uuid);
}
