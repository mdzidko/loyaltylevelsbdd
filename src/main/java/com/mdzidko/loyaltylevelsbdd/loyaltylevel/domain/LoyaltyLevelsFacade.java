package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
                .map(LoyaltyLevel::dto)
                .collect(Collectors.toList());
    }

    public LoyaltyLevelDto findByUuid(UUID uuid) {
        return loyaltyLevelsRepository.findByUuid(uuid);
    }

    public void update(UUID fromString, LoyaltyLevelDto loyaltyLevel) {
    }
}
