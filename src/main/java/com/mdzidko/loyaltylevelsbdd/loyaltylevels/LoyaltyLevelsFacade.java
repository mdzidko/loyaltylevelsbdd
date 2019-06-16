package com.mdzidko.loyaltylevelsbdd.loyaltylevels;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoyaltyLevelsFacade {

    private final LoyaltyLevelsRepository loyaltyLevelsRepository;
    private final LoyaltyLevelConverter converter;

    public LoyaltyLevelsFacade(LoyaltyLevelsRepository loyaltyLevelsRepository, LoyaltyLevelConverter converter) {

        this.loyaltyLevelsRepository = loyaltyLevelsRepository;
        this.converter = converter;
    }

    public LoyaltyLevelsFacade add(LoyaltyLevelDto... levels) {

        Arrays.stream(levels)
                .map(converter::convert)
                .forEach(loyaltyLevelsRepository::save);

        return this;
    }

    public List<LoyaltyLevelDto> findAll() {
        return loyaltyLevelsRepository.findAll().stream()
                .map(level -> level.dto())
                .collect(Collectors.toList());
    }
}
