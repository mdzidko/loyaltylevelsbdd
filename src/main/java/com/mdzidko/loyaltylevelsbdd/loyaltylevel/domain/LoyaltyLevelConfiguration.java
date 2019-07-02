package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoyaltyLevelConfiguration {

    LoyaltyLevelFacade loyaltyLevelsFacade(){

        return loyaltyLevelsFacade(new InMemoryLoyaltyLevelRepository());
    }

    @Bean
    LoyaltyLevelFacade loyaltyLevelsFacade(LoyaltyLevelRepository loyaltyLevelRepository){

        return new LoyaltyLevelFacade(loyaltyLevelRepository, new LoyaltyLevelFactory(loyaltyLevelRepository));
    }
}
