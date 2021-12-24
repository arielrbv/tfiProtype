package com.nutritionx.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Patology;

public interface PatologyRepository extends JpaRepository<Patology, String>{
	
	//find all patologies
	List<Patology> findAll();

}
