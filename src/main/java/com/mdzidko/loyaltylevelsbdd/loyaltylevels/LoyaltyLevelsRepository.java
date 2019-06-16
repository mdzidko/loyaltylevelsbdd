package com.mdzidko.loyaltylevelsbdd.loyaltylevels;

import java.util.List;

interface LoyaltyLevelsRepository {

    LoyaltyLevel save(LoyaltyLevel level);

    List<LoyaltyLevel> findAll();
}
