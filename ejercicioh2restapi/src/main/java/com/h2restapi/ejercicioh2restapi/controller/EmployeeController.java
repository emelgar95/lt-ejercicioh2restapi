package com.h2restapi.ejercicioh2restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.h2restapi.ejercicioh2restapi.entity.EmployeeEntity;
import com.h2restapi.ejercicioh2restapi.entity.EmployeeSearch;
import com.h2restapi.ejercicioh2restapi.entity.PersonEntity;
import com.h2restapi.ejercicioh2restapi.entity.PositionEntity;
import com.h2restapi.ejercicioh2restapi.repository.EmployeeRepository;
import com.h2restapi.ejercicioh2restapi.repository.PersonRepository;
import com.h2restapi.ejercicioh2restapi.repository.PositionRepository;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	PositionRepository positionRepository;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeEntity> save(@RequestBody EmployeeEntity employeeEntity){
		try {
			return new ResponseEntity<>(employeeRepository.save(employeeEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/employee/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployeeEntity(@RequestBody EmployeeEntity employeeEntity, @PathVariable Long id){
		try {
			Optional<EmployeeEntity> employee = employeeRepository.findById(id);
			if(employee.isPresent()){
				return new ResponseEntity<EmployeeEntity>(employeeRepository.save(employeeEntity), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<EmployeeEntity> deleteEmployeeEntity(@PathVariable Long id){
		try {
			Optional<EmployeeEntity> employee = employeeRepository.findById(id);
			if(employee.isPresent()){
				employeeRepository.delete(employee.get());
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeEntity>> getAllPersons(@RequestBody EmployeeSearch employeeSearch){
		try {
			List<EmployeeEntity> employeeList = employeeRepository.findAll();
			if(employeeSearch.getName() == null && employeeSearch.getPosition() == null) {
				return new ResponseEntity<List<EmployeeEntity>>(employeeList,HttpStatus.OK);
			}else if (employeeSearch.getName() != null){
				List<PersonEntity> persons = personRepository.findByQuery(employeeSearch.getName());
				employeeList.removeAll(employeeList);
				if(persons.isEmpty()) {
					return new ResponseEntity<List<EmployeeEntity>>(employeeList,HttpStatus.OK);
				}
				for (int i = 0; i<persons.size(); i++) {
		            long element = persons.get(i).getId();
		            List<EmployeeEntity> employees = employeeRepository.findByQueryPerson(element);
		            employeeList.addAll(employees);
		        }
				return new ResponseEntity<List<EmployeeEntity>>(employeeList,HttpStatus.OK);
			}else if (employeeSearch.getPosition() != null){
				List<PositionEntity> position = positionRepository.findByQuery(employeeSearch.getPosition());
				employeeList.removeAll(employeeList);
				if(position.isEmpty()) {
					return new ResponseEntity<List<EmployeeEntity>>(employeeList,HttpStatus.OK);
				}
				for (int i = 0; i<position.size(); i++) {
		            long element = position.get(i).getId();
		            List<EmployeeEntity> employees = employeeRepository.findByQueryPosition(element);
		            employeeList.addAll(employees);
		        }
			}
			Optional<EmployeeEntity> employeeS = employeeRepository.findById((long) 1);
			employeeS.ifPresent(employeeList::add);
			return new ResponseEntity<List<EmployeeEntity>>(employeeList,HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
