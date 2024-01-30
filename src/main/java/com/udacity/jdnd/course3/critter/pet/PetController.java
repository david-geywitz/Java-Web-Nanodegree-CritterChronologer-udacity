package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@Valid @RequestBody PetDTO petDTO) {
        Pet pet = petService.save(petDTOToEntity(petDTO));

        return entityToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return entityToPetDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.list()
                .stream()
                .map(this::entityToPetDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.allWithOwner(ownerId)
                .stream()
                .map(this::entityToPetDTO)
                .collect(Collectors.toList());
    }

    private Pet petDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer owner = customerService.findById(petDTO.getOwnerId());
        pet.setOwner(owner);

        return pet;
    }

    private PetDTO entityToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if(pet.getOwner() != null) {
            petDTO.setOwnerId(pet.getOwner().getId());
        }

        return petDTO;
    }
}
