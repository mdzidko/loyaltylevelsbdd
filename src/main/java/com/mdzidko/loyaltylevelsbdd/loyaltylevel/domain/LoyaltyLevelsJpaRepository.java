package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface LoyaltyLevelsJpaRepository extends JpaRepository<LoyaltyLevel, Long> {

    boolean existsByName(String name);
}

@Repository
class LoyaltyLevelsRepositoryJpaImpl implements LoyaltyLevelsRepository {

    private final LoyaltyLevelsJpaRepository loyaltyLevelsJpaRepository;

    public LoyaltyLevelsRepositoryJpaImpl(LoyaltyLevelsJpaRepository loyaltyLevelsJpaRepository) {
        this.loyaltyLevelsJpaRepository = loyaltyLevelsJpaRepository;
    }

    @Override
    public LoyaltyLevel save(LoyaltyLevel loyaltyLevel) {
        return loyaltyLevelsJpaRepository.save(loyaltyLevel);
    }

    @Override
    public List<LoyaltyLevel> findAll() {
        return loyaltyLevelsJpaRepository.findAll();
    }

    @Override
    public void delete(LoyaltyLevel loyaltyLevel) {
        loyaltyLevelsJpaRepository.delete(loyaltyLevel);

    }

    @Override
    public boolean existsByName(String name) {
        return loyaltyLevelsJpaRepository.existsByName(name);
    }

    @Override
    public Optional<LoyaltyLevel> findById(long id) {
        return loyaltyLevelsJpaRepository.findById(id);
    }
}
