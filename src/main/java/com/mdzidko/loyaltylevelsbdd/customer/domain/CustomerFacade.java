package com.mdzidko.loyaltylevelsbdd.customer.domain;


import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerFacade {

    private CustomerRepository customerRepository;

    public CustomerFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerFacade add(CustomerDto customer) {
        return this;
    }

    public List<CustomerDto> findAll() {
        return new ArrayList<CustomerDto>();
    }
}
