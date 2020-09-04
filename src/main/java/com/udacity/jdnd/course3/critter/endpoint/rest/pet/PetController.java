package com.udacity.jdnd.course3.critter.endpoint.rest.pet;

import com.udacity.jdnd.course3.critter.application.PetService;
import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.endpoint.dto.PetDTO;
import com.udacity.jdnd.course3.critter.infrastructure.exception.handler.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) throws ResourceNotFoundException {
        Pet newPet = petService.savePet(this.mapPetDTOtoPet(petDTO), petDTO.getOwnerId());
        return this.mapPetToPetDTO(newPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) throws ResourceNotFoundException {
        Pet pet = petService.getPetById(petId);
        return this.mapPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAllPets().stream().map(this::mapPetToPetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) throws ResourceNotFoundException {
        return petService.getAllByOwnerId(ownerId).stream().map(this::mapPetToPetDTO).collect(Collectors.toList());
    }

    private PetDTO mapPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private Pet mapPetDTOtoPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
