package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDoesntExistException;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelExistsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoyaltyLevelsFacade {

    private final LoyaltyLevelsRepository loyaltyLevelsRepository;
    private final LoyaltyLevelConverter converter;

    public LoyaltyLevelsFacade(LoyaltyLevelsRepository loyaltyLevelsRepository, LoyaltyLevelConverter converter) {

        this.loyaltyLevelsRepository = loyaltyLevelsRepository;
        this.converter = converter;
    }

    public LoyaltyLevelsFacade add(LoyaltyLevelDto level) {

        if(loyaltyLevelsRepository.loyaltyLevelExists(level.getName()))
            throw new LoyaltyLevelExistsException();

        loyaltyLevelsRepository.save(converter.convert(level));

        return this;
    }

    public List<LoyaltyLevelDto> findAll() {
        return loyaltyLevelsRepository.findAll().stream()
                .map(LoyaltyLevel::dto)
                .collect(Collectors.toList());
    }

    public LoyaltyLevelsFacade remove(String name) {

        Optional<LoyaltyLevel> loyaltyLevel = loyaltyLevelsRepository.findByName(name);

        loyaltyLevelsRepository.delete(loyaltyLevel.orElseThrow(LoyaltyLevelDoesntExistException::new));

        return this;

    }
}
