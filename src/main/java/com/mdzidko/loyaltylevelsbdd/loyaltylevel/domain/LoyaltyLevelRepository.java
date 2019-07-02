package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import java.util.List;
import java.util.Optional;

interface LoyaltyLevelRepository {

    LoyaltyLevel save(LoyaltyLevel level);

    List<LoyaltyLevel> findAll();

    void delete(LoyaltyLevel loyaltyLevel);

    boolean existsByName(String name);

    Optional<LoyaltyLevel> findById(long id);
}
