package com.udacity.jdnd.course3.critter.domain.repository;

import com.udacity.jdnd.course3.critter.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetsByOwnerId(long ownerId);
}
