package com.udacity.jdnd.course3.critter.application;

import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.infrastructure.exception.handler.ResourceNotFoundException;

import java.util.List;

public interface PetService {
    Pet getPetById(long id) throws ResourceNotFoundException;

    Pet savePet(Pet pet, long ownerId) throws ResourceNotFoundException;

    List<Pet> getAllPets();

    List<Pet> getAllByOwnerId(long ownerId) throws ResourceNotFoundException;
}
