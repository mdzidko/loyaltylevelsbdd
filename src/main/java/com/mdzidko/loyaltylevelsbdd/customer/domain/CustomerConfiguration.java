package com.mdzidko.loyaltylevelsbdd.customer.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CustomerConfiguration {
    CustomerFacade customerFacade(){

        return loyaltyLevelsFacade(new InMemoryCustomerRepository());
    }

    @Bean
    CustomerFacade loyaltyLevelsFacade(CustomerRepository customerRepository){

        return new CustomerFacade(customerRepository, new CustomerFactory(customerRepository));
    }
}
