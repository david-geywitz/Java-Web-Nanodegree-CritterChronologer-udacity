package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetRepository petRepository;

    public List<Pet> list() {
        return petRepository.findAll();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(PetNotFoundException::new);
    }

    public Pet save(Pet pet) {
        Pet petSaved = petRepository.save(pet);

        Customer owner = customerService.findById(petSaved.getOwner().getId());
        owner.getPets().add(petSaved);
        customerService.save(owner);

        return petSaved;
    }

    public List<Pet> allWithOwner(Long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
