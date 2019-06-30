package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDataValidationException;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelNameDuplicationException;

class LoyaltyLevelFactory {

    private final LoyaltyLevelsRepository loyaltyLevelsRepository;

    LoyaltyLevelFactory(LoyaltyLevelsRepository loyaltyLevelsRepository) {
        this.loyaltyLevelsRepository = loyaltyLevelsRepository;
    }

    LoyaltyLevel create(LoyaltyLevelDto levelDto) {

        validate(levelDto);

        return new LoyaltyLevel(levelDto.getName(),
                                levelDto.getPointsBonusPercentage(),
                                levelDto.getLowerLevelBound());
    }

    private void validate(LoyaltyLevelDto levelDto) {

        if(levelDto.getName() == null || levelDto.getName().isEmpty())
            throw new LoyaltyLevelDataValidationException("Loyalty level name can't be empty");

        if(levelDto.getLowerLevelBound() < 0)
            throw new LoyaltyLevelDataValidationException("Value of lower level bound can't be lower than 0");

        if(levelDto.getPointsBonusPercentage() < 0)
            throw new LoyaltyLevelDataValidationException("Value of points bonus percentage cant't be lower than 0");

        if(loyaltyLevelsRepository.existsByName(levelDto.getName()))
            throw new LoyaltyLevelNameDuplicationException();
    }
}
