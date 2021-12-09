package com.h2restapi.ejercicioh2restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.h2restapi.ejercicioh2restapi.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>{
	@Query("FROM employee WHERE person_id = ?1")
	List<EmployeeEntity> findByQueryPerson(Long person);
	
	@Query("FROM employee WHERE position_id = ?1")
	List<EmployeeEntity> findByQueryPosition(Long position);
}
