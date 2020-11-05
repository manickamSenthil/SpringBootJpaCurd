package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.PetsServiceImpl;
import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.Pets;

/**
 * 
 * @author Senthil
 *
 */
@RestController
@RequestMapping("/api/v1")
public class PetsController {

	@Autowired
	private PetsServiceImpl petsServiceImpl;

	@GetMapping("/pets")
	public List<Pets> getAllPets() {
		List<Pets> petList = petsServiceImpl.getPets();
		return petList;
	}

	@GetMapping("/pets/{petid}")
	public Optional<Pets> getPetssByInstructor(@PathVariable(value = "petid") Long petId) {
		return petsServiceImpl.findPetsById(petId);
	}

	@PostMapping("/pets")
	public Pets createPets(@RequestBody Pets pets) throws ResourceNotFoundException {
		return petsServiceImpl.insertPet(pets);
	}

	@PutMapping("/pets/{petid}")
	public Pets updatePets(@PathVariable(value = "petid") Long petId, @RequestBody Pets petsRequest)
			throws ResourceNotFoundException {
		Pets updatedPet = petsServiceImpl.updatePet(petId, petsRequest);
		return updatedPet;
	}

	@DeleteMapping("/pets/{petid}")
	public Map<String, Boolean> deletePets(@PathVariable(value = "petid") Long petId) throws ResourceNotFoundException {
		Map<String, Boolean> deletedPet = petsServiceImpl.deletePet(petId);
		return deletedPet;
	}

}
