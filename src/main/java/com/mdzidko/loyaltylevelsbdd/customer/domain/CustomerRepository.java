package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

interface CustomerRepository {

    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);
}
