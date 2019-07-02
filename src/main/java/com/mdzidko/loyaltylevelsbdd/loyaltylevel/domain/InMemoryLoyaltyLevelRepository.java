package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import java.util.*;

class InMemoryLoyaltyLevelRepository implements LoyaltyLevelRepository {

    private final Map<Long, LoyaltyLevel> loyaltyLevels = new HashMap<>();
    private long lastId = 0;

    @Override
    public LoyaltyLevel save(LoyaltyLevel level) {

        ++lastId;
        level.setId(lastId);

        loyaltyLevels.put(lastId, level);
        return level;
    }

    @Override
    public List<LoyaltyLevel> findAll() {
        return new ArrayList<>(loyaltyLevels.values());
    }

    @Override
    public Optional<LoyaltyLevel> findById(long id) {

        LoyaltyLevel found = loyaltyLevels.get(id);

        return Optional.ofNullable(found);
    }

    @Override
    public void delete(LoyaltyLevel loyaltyLevel) {

        loyaltyLevels.remove(loyaltyLevel.getId());
    }

    @Override
    public boolean existsByName(String name) {
        return loyaltyLevels.values().stream().anyMatch(level -> level.getName().equals(name));
    }
}
