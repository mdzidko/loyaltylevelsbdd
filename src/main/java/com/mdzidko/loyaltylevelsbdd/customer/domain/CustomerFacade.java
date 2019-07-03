package com.mdzidko.loyaltylevelsbdd.customer.domain;


import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;
import com.mdzidko.loyaltylevelsbdd.customer.dto.LoyaltyLevelDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerFacade {

    private CustomerRepository customerRepository;
    private CustomerFactory customerFactory;

    public CustomerFacade(CustomerRepository customerRepository, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    public CustomerFacade add(String cardNumber, Set<LoyaltyLevelDto> loyaltyLevelsConfiguration) {

        customerRepository.save(customerFactory.create(cardNumber, loyaltyLevelsConfiguration));

        return this;
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream()
                    .map(Customer::dto)
                    .collect(Collectors.toList());
    }

    public Optional<CustomerDto> findByCardNumber(String cardNumber) {
        Optional<Customer> customer = customerRepository.findByCardNumber(cardNumber);

        return customer.map(Customer::dto);
    }
}
