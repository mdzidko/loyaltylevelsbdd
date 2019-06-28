package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoyaltyLevelsConfiguration {

    LoyaltyLevelsFacade loyaltyLevelsFacade(){

        return loyaltyLevelsFacade(new InMemoryLoyaltyLevelsRepository());
    }

    @Bean
    LoyaltyLevelsFacade loyaltyLevelsFacade(LoyaltyLevelsRepository loyaltyLevelsRepository){

        return new LoyaltyLevelsFacade(loyaltyLevelsRepository, new LoyaltyLevelFactory(loyaltyLevelsRepository));
    }
}
