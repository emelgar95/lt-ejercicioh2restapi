package com.h2restapi.ejercicioh2restapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.h2restapi.ejercicioh2restapi.entity.PositionEntity;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity,Long> {
	@Query("FROM position WHERE name = ?1")
	List<PositionEntity> findByQuery(String name);
}
