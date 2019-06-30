package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDoesntExistException;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoyaltyLevelsFacade {

    private final LoyaltyLevelsRepository loyaltyLevelsRepository;
    private final LoyaltyLevelFactory loyaltyLevelFactory;

    public LoyaltyLevelsFacade(LoyaltyLevelsRepository loyaltyLevelsRepository, LoyaltyLevelFactory loyaltyLevelFactory) {

        this.loyaltyLevelsRepository = loyaltyLevelsRepository;
        this.loyaltyLevelFactory = loyaltyLevelFactory;
    }

    public LoyaltyLevelsFacade add(LoyaltyLevelDto level) {

        loyaltyLevelsRepository.save(loyaltyLevelFactory.create(level));
        return this;
    }

    public List<LoyaltyLevelDto> findAll() {

        return loyaltyLevelsRepository.findAll().stream()
                .map(LoyaltyLevel::dto)
                .collect(Collectors.toList());
    }

    public LoyaltyLevelsFacade remove(long id) {

        Optional<LoyaltyLevel> loyaltyLevel = loyaltyLevelsRepository.findById(id);
        loyaltyLevelsRepository.delete(loyaltyLevel.orElseThrow(LoyaltyLevelDoesntExistException::new));

        return this;

    }
}
