package com.udacity.jdnd.course3.critter.application.impl;

import com.udacity.jdnd.course3.critter.application.PetService;
import com.udacity.jdnd.course3.critter.domain.model.Customer;
import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.domain.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.domain.repository.PetRepository;
import com.udacity.jdnd.course3.critter.infrastructure.exception.handler.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Pet getPetById(long id) throws ResourceNotFoundException {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found."));
    }

    @Override
    @Transactional
    public Pet savePet(Pet pet, long ownerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        pet.setOwner(customer);
        Pet newPet = petRepository.save(pet);

        if(customer.getPets() == null)
            customer.setPets(new ArrayList<>());
        customer.getPets().add(newPet);
        customerRepository.save(customer);

        return newPet;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pet> getAllByOwnerId(long ownerId) throws ResourceNotFoundException{
        customerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return petRepository.findPetsByOwnerId(ownerId);
    }
}
