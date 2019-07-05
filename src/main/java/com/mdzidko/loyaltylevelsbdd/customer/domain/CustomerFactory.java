package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerCardNumberDuplicationException;
import com.mdzidko.loyaltylevelsbdd.customer.dto.LoyaltyLevelDto;

import java.util.Set;

class CustomerFactory {

    private CustomerRepository customerRepository;

    CustomerFactory(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    Customer create(String cardNumber, Set<LoyaltyLevelDto> loyaltyLevelsConfiguration) {

        if(customerRepository.existsByCardNumber(cardNumber))
            throw new CustomerCardNumberDuplicationException();

        String defaultLoyaltyLevel = loyaltyLevelsConfiguration.stream()
                                        .filter(LoyaltyLevelDto::isDefault)
                                        .findFirst().get().getName();

        return new Customer(cardNumber, defaultLoyaltyLevel, 0.0);
    }
}
