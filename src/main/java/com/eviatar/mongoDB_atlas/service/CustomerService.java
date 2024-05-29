package com.eviatar.mongoDB_atlas.service;

import com.eviatar.mongoDB_atlas.model.Customer;
import com.eviatar.mongoDB_atlas.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> findById(String CustomerId) {
        return customerRepository.findById(CustomerId);
    }

    public Customer createOneCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> createCustomers(List<Customer> customer) {
        return customerRepository.saveAll(customer);
    }

}
