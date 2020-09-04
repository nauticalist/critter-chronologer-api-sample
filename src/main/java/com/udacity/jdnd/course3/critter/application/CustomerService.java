package com.udacity.jdnd.course3.critter.application;

import com.udacity.jdnd.course3.critter.domain.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer, List<Long> petIds);
}
