package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Pets;

public interface PetsService {
	public List<Pets> getPets();

	public Pets insertPet(Pets pet) throws ResourceNotFoundException;

	public Pets updatePet(Long petId, Pets petDetails) throws ResourceNotFoundException;

	public Map<String, Boolean> deletePet(Long petId) throws ResourceNotFoundException;
}
