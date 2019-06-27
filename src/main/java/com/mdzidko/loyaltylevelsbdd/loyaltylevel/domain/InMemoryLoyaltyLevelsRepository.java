package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import java.util.*;

public class InMemoryLoyaltyLevelsRepository implements LoyaltyLevelsRepository {

    private Map<String, LoyaltyLevel> loyaltyLevels = new HashMap<>();

    @Override
    public LoyaltyLevel save(LoyaltyLevel level) {
        loyaltyLevels.put(level.getName(), level);
        return level;
    }

    @Override
    public List<LoyaltyLevel> findAll() {
        return new ArrayList<>(loyaltyLevels.values());
    }

    @Override
    public Optional<LoyaltyLevel> findByName(String name) {

        LoyaltyLevel found = loyaltyLevels.get(name);

        return Optional.ofNullable(found);
    }

    @Override
    public void delete(LoyaltyLevel loyaltyLevel) {

        loyaltyLevels.remove(loyaltyLevel.getName());
    }

    @Override
    public boolean loyaltyLevelExists(String name) {
        return loyaltyLevels.values().stream().anyMatch(level -> level.getName().equals(name));
    }
}
