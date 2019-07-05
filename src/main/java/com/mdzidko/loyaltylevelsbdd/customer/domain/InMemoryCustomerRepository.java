package com.mdzidko.loyaltylevelsbdd.customer.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


class InMemoryCustomerRepository implements CustomerRepository {

    private HashMap<String, Customer> customers = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        return customers.put(customer.getCardNumber(), customer);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findByCardNumber(String cardNumber) {
        return Optional.ofNullable(customers.get(cardNumber));
    }

    @Override
    public boolean existsByCardNumber(String cardNumber) {
        return customers.containsKey(cardNumber);
    }


}
