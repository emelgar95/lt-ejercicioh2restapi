package com.h2restapi.ejercicioh2restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.h2restapi.ejercicioh2restapi.entity.EmployeeEntity;
import com.h2restapi.ejercicioh2restapi.entity.EmployeeSearchPosition;
import com.h2restapi.ejercicioh2restapi.entity.PositionEntity;
import com.h2restapi.ejercicioh2restapi.entity.PositionSearch;
import com.h2restapi.ejercicioh2restapi.repository.EmployeeRepository;
import com.h2restapi.ejercicioh2restapi.repository.PositionRepository;

@RestController
public class PositionController {
	@Autowired
	PositionRepository positionRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/position")
	public ResponseEntity<PositionEntity> save(@RequestBody PositionEntity positionEntity){
		try {
			return new ResponseEntity<>(positionRepository.save(positionEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/position")
	public ResponseEntity<List<PositionSearch>> getAllPersons(){
		try {
			List<PositionEntity> positionList = positionRepository.findAll();
			List<PositionSearch> positionSearch = new ArrayList<PositionSearch>();
			for (int i = 0; i<positionList.size(); i++) {
	            long element = positionList.get(i).getId();
	            PositionSearch position = new PositionSearch();
	            position.setId(element);
	            position.setName(positionList.get(i).getName());
	            List<EmployeeEntity> employeeList = employeeRepository.findByQueryPosition(element);
	            List<EmployeeSearchPosition> employeeSP = new ArrayList<EmployeeSearchPosition>();
	            for (int j = 0; j<employeeList.size(); j++) {
	            	EmployeeSearchPosition employeeL = new EmployeeSearchPosition();
		            employeeL.setId(employeeList.get(j).getId());
		            employeeL.setPerson(employeeList.get(j).getPerson());
		            employeeL.setSalary(employeeList.get(j).getSalary());
		            employeeSP.add(employeeL);
		        }
	            position.setEmployee(employeeSP);
	            positionSearch.add(position);
	        }

			return new ResponseEntity<List<PositionSearch>>(positionSearch,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
