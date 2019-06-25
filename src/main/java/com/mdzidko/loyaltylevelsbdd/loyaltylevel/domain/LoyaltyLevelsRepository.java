package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import java.util.List;
import java.util.UUID;

interface LoyaltyLevelsRepository {

    LoyaltyLevel save(LoyaltyLevel level);

    List<LoyaltyLevel> findAll();
    LoyaltyLevel findByUuid(UUID uuid);

    void delete(LoyaltyLevel loyaltyLevel);
}
