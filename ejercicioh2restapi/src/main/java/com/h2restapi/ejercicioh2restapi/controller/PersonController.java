package com.h2restapi.ejercicioh2restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.h2restapi.ejercicioh2restapi.entity.PersonEntity;
import com.h2restapi.ejercicioh2restapi.repository.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	@PostMapping("/person")
	public ResponseEntity<PersonEntity> save(@RequestBody PersonEntity personEntity){
		try {
			return new ResponseEntity<>(personRepository.save(personEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/person")
	public ResponseEntity<List<PersonEntity>> getAllPersons(){
		try {
			List<PersonEntity> personList = personRepository.findAll();
			return new ResponseEntity<List<PersonEntity>>(personList, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/person/{id}")
	public ResponseEntity<PersonEntity> getPerson(@PathVariable Long id){
		Optional<PersonEntity> personEntity = personRepository.findById(id);
		if(personEntity.isPresent()) {
			return new ResponseEntity<PersonEntity>(personEntity.get(), HttpStatus.OK);
		}
		return new ResponseEntity<PersonEntity>(HttpStatus.NOT_FOUND);
	}
}
