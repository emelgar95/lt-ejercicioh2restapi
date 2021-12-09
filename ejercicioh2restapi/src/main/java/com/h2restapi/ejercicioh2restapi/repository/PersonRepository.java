package com.h2restapi.ejercicioh2restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.h2restapi.ejercicioh2restapi.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository <PersonEntity,Long>{
	@Query("FROM person WHERE name = ?1")
	List<PersonEntity> findByQuery(String name);
}
