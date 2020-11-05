package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Person;

public interface PersonService {
	public List<Person> getPresons();

	public Person insertPresons(Person person) throws ResourceNotFoundException;

	public Person updatePresons(Long personId, Person personDetails) throws ResourceNotFoundException;

	public Map<String, Boolean> deletePresons(Long personId) throws ResourceNotFoundException;

}
