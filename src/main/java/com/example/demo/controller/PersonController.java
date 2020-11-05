package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Person;
import com.example.service.PersonServiceImpl;

/***
 * 
 * @author Senthil
 *
 */
@RestController
@RequestMapping("/api/v1")
public class PersonController {

	@Autowired
	private PersonServiceImpl personServiceImpl;

	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		return personServiceImpl.getPresons();
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personServiceImpl.getPresonsByid(personId);
		return ResponseEntity.ok().body(person);
	}

	@PostMapping("/persons")
	public Person createPerson(@RequestBody Person person) throws ResourceNotFoundException {
		Person insertPerson = personServiceImpl.insertPresons(person);
		return insertPerson;
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,
			@RequestBody Person personDetails) throws ResourceNotFoundException {
		Person updatedPersondetail = personServiceImpl.updatePresons(personId, personDetails);
		return ResponseEntity.ok(updatedPersondetail);
	}

	@DeleteMapping("/persons/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Map<String, Boolean> deleted = personServiceImpl.deletePresons(personId);
		return deleted;
	}

	@GetMapping("/persons/anyname/{name}")
	public List<Person> getPersonByName(@PathVariable(value = "name") String name) throws ResourceNotFoundException {
		List<Person> byFirstName = personServiceImpl.findByPersonName(name);
		return byFirstName;
	}

	@GetMapping("/persons/firstname/{firstname}")
	public List<Person> getPersonByFirstName(@PathVariable(value = "firstname") String firstName)
			throws ResourceNotFoundException {
		return personServiceImpl.findByPersonFirstName(firstName);
	}

	@GetMapping("/persons/lastname/{lastname}")
	public List<Person> getPersonByLastName(@PathVariable(value = "lastname") String lastName)
			throws ResourceNotFoundException {
		return personServiceImpl.findByPersonLastName(lastName);
	}

	@GetMapping("/persons/bothname")
	public List<Person> getPersonByBothName(@RequestParam(name = "firstname") String firstName,
			@RequestParam(name = "lastname") String lastName) throws ResourceNotFoundException {
		return personServiceImpl.findByPersonBothName(firstName, lastName);
	}

}
