package com.udacity.jdnd.course3.critter.application.impl;

import com.udacity.jdnd.course3.critter.application.CustomerService;
import com.udacity.jdnd.course3.critter.domain.model.Customer;
import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.domain.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return customerRepository.findAll(sort);
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map(petRepository::getOne).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customerRepository.save(customer);
    }
}
