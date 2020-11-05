package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

/****
 * 
 * @author Senthil
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> getPresons() {
		return personRepository.findAll();
	}

	/****
	 * insert person
	 */
	@Override
	public Person insertPresons(Person person) throws ResourceNotFoundException {
		if (person.getFirstName().equals(person.getLastName()))
			throw new ResourceNotFoundException(
					"Person's first name and last name should not be same :: " + person.getFirstName());
		Person insertPerson = personRepository.save(person);
		return insertPerson;
	}

	/****
	 * update person details
	 */
	@Override
	public Person updatePresons(Long personId, Person personDetails) throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.getAuthorities().stream().count() == 1
				&& !person.getAddress().equals(personDetails.getAddress())) {
			throw new ResourceNotFoundException("User role does not have access to update the address");
		}

		if (person.getFirstName().equals(person.getLastName()))
			throw new ResourceNotFoundException(
					"Person's first name and last name should not be same :: " + person.getFirstName());

		person.setDob(personDetails.getDob());
		person.setLastName(personDetails.getLastName());
		person.setFirstName(personDetails.getFirstName());
		person.setAddress(personDetails.getAddress());
		Person updatedPersonDetail = personRepository.save(person);
		return updatedPersonDetail;
	}

	/***
	 * delete person
	 */
	@Override
	public Map<String, Boolean> deletePresons(Long personId) throws ResourceNotFoundException {
		Person Person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		personRepository.delete(Person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/***
	 * get person details using id
	 * 
	 * @param personId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Person getPresonsByid(Long personId) throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		return person;
	}

	/***
	 * get person details using firstname or lastname
	 * 
	 * @param name
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Person> findByPersonName(String name) throws ResourceNotFoundException {
		List<Person> byFirstName = personRepository.findByFirstName(name);
		List<Person> byLastName = personRepository.findByLastName(name);
		byFirstName.addAll(byLastName);
		return byFirstName;
	}

	/***
	 * get person details using firstname
	 * 
	 * @param firstname
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Person> findByPersonFirstName(String firstName) throws ResourceNotFoundException {
		return personRepository.findByFirstName(firstName);
	}

	/***
	 * get person details using LastName
	 * 
	 * @param lastname
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Person> findByPersonLastName(String lastName) throws ResourceNotFoundException {
		return personRepository.findByLastName(lastName);
	}

	/***
	 * get person details using Firstname and lastname
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Person> findByPersonBothName(String firstName, String lastName) throws ResourceNotFoundException {
		return personRepository.findByFirstNameAndLastName(firstName, lastName);
	}

}
