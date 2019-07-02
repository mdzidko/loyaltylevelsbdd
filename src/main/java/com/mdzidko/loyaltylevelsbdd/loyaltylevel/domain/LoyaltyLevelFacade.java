package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDoesntExistException;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoyaltyLevelFacade {

    private final LoyaltyLevelRepository loyaltyLevelRepository;
    private final LoyaltyLevelFactory loyaltyLevelFactory;

    public LoyaltyLevelFacade(LoyaltyLevelRepository loyaltyLevelRepository, LoyaltyLevelFactory loyaltyLevelFactory) {

        this.loyaltyLevelRepository = loyaltyLevelRepository;
        this.loyaltyLevelFactory = loyaltyLevelFactory;
    }

    public LoyaltyLevelFacade add(LoyaltyLevelDto level) {

        loyaltyLevelRepository.save(loyaltyLevelFactory.create(level));
        return this;
    }

    public List<LoyaltyLevelDto> findAll() {

        return loyaltyLevelRepository.findAll().stream()
                .map(LoyaltyLevel::dto)
                .collect(Collectors.toList());
    }

    public LoyaltyLevelFacade remove(long id) {

        Optional<LoyaltyLevel> loyaltyLevel = loyaltyLevelRepository.findById(id);
        loyaltyLevelRepository.delete(loyaltyLevel.orElseThrow(LoyaltyLevelDoesntExistException::new));

        return this;

    }
}
