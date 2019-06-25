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
    public LoyaltyLevel findByUuid(UUID uuid) {

        LoyaltyLevel fount = loyaltyLevels.get(uuid);

        return loyaltyLevels.get(uuid);
    }

    @Override
    public void delete(LoyaltyLevel loyaltyLevel) {

        loyaltyLevels.remove(loyaltyLevel.getUuid());
    }

    @Override
    public boolean loyaltyLevelExists(String name) {
        return loyaltyLevels.values().stream().anyMatch(level -> level.getName().equals(name));
    }
}
