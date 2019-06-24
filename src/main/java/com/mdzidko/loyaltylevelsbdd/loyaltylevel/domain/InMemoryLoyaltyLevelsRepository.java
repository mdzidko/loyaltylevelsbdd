package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

import java.util.*;

public class InMemoryLoyaltyLevelsRepository implements LoyaltyLevelsRepository {

    private Map<UUID, LoyaltyLevel> loyaltyLevels = new HashMap<>();

    @Override
    public LoyaltyLevel save(LoyaltyLevel level) {
        loyaltyLevels.put(level.getUuid(), level);
        return level;
    }

    @Override
    public List<LoyaltyLevel> findAll() {
        return new ArrayList<>(loyaltyLevels.values());
    }

    @Override
    public LoyaltyLevelDto findByUuid(UUID uuid) {
        return loyaltyLevels.get(uuid).dto();
    }
}
