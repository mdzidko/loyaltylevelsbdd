
package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface LoyaltyLevelJpaRepository extends JpaRepository<LoyaltyLevel, Long> {

    boolean existsByName(String name);
}

@Repository
class LoyaltyLevelRepositoryJpaImpl implements LoyaltyLevelRepository {

    private final LoyaltyLevelJpaRepository loyaltyLevelJpaRepository;

    public LoyaltyLevelRepositoryJpaImpl(LoyaltyLevelJpaRepository loyaltyLevelJpaRepository) {
        this.loyaltyLevelJpaRepository = loyaltyLevelJpaRepository;
    }

    @Override
    public LoyaltyLevel save(LoyaltyLevel loyaltyLevel) {
        return loyaltyLevelJpaRepository.save(loyaltyLevel);
    }

    @Override
    public List<LoyaltyLevel> findAll() {
        return loyaltyLevelJpaRepository.findAll();
    }

    @Override
    public void delete(LoyaltyLevel loyaltyLevel) {
        loyaltyLevelJpaRepository.delete(loyaltyLevel);

    }

    @Override
    public boolean existsByName(String name) {
        return loyaltyLevelJpaRepository.existsByName(name);
    }

    @Override
    public Optional<LoyaltyLevel> findById(long id) {
        return loyaltyLevelJpaRepository.findById(id);
    }

    @Override
    public boolean existsAnyDefault() {
        return false;
    }
}
