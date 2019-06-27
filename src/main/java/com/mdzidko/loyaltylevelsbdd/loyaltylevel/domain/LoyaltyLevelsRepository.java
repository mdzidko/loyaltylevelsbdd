package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface LoyaltyLevelsRepository {

    LoyaltyLevel save(LoyaltyLevel level);

    List<LoyaltyLevel> findAll();

    void delete(LoyaltyLevel loyaltyLevel);

    boolean loyaltyLevelExists(String name);

    Optional<LoyaltyLevel> findByName(String name);
}
