package com.nutritionx.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Breakfast;

public interface BreakfastRepository extends JpaRepository<Breakfast, String>{

	//Breakfast findByBreakfastId(String id);
	
}
