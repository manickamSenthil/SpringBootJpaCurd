package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pets;

/***
 * 
 * @author Senthil
 *
 */
@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {

}
