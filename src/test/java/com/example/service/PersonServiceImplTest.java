package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.Application;
import com.example.demo.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class PersonServiceImplTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Before
	public void startUp() {
		restTemplate = new TestRestTemplate("user", "user");
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void securityTest() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/persons", HttpMethod.GET,
				entity, String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	public void testInsertPresons() {
		Person Person = new Person();
		Person.setDob("12/12/1993");
		Person.setFirstName("admin");
		Person.setLastName("admin");
		Person.setAddress("madurai");
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/persons", Person,
				Person.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePresons() {
		testInsertPresons();
		int id = 1;
		Person person = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/" + id, Person.class);
		person.setFirstName("userlogin");
		person.setLastName("user");
		person.setAddress("mk");
		restTemplate.put(getRootUrl() + "/api/v1/persons/" + id, person);

		Person updatedPerson = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/" + id, Person.class);
		
		assertEquals("mk", updatedPerson.getAddress());
		assertNotNull(updatedPerson);
	}

	@Test
	public void testGetAllPersons() {
		testInsertPresons();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/persons", HttpMethod.GET,
				entity, String.class);
		System.out.println(response.getBody());
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetPersonById() {
		testInsertPresons();
		Person Person = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/1", Person.class);
		System.out.println(Person.getFirstName());
		assertNotNull(Person);
	}

	@Test
	public void testDeletePresons() {
		testInsertPresons();
		int id = 5;
		Person Person = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/" + id, Person.class);
		assertNotNull(Person);

		restTemplate.delete(getRootUrl() + "/api/v1/persons/" + id);

		try {
			Person = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/" + id, Person.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testGetPersonByFirstName() {
		testInsertPresons();
		Person Person = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/firstname/senthil", Person.class);
		System.out.println(Person.getFirstName());
		assertEquals("senthil", Person.getFirstName());
		assertNotNull(Person);
	}

	@Test
	public void testGetPersonByLastName() {
		testInsertPresons();
		// restTemplate = new TestRestTemplate("user", "user");
		Person Person = restTemplate.getForObject(getRootUrl() + "/api/v1/persons/lastname/senthil", Person.class);
		System.out.println(Person.getFirstName());
		assertEquals("senthil", Person.getLastName());
		assertNotNull(Person);
	}
}
