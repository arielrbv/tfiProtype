package com.nutritionx.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, String>{
	
	Professional findByProfessionalId(String id);
	
	Professional findByEmailAndPassword(String email, String pass);
	
}
