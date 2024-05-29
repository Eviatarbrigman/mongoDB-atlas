package com.eviatar.mongoDB_atlas.controller;

import com.eviatar.mongoDB_atlas.model.Customer;
import com.eviatar.mongoDB_atlas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createOneCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createOneCustomer(customer));
    }


    @PostMapping("/createMultipleCustomers")
    public ResponseEntity<List<Customer>> createCustomers(@RequestBody List<Customer> customer) {
        return ResponseEntity.ok(customerService.createCustomers(customer));
    }

}
