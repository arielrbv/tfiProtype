package com.nutritionx.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Meals;

public interface MealsRepository extends JpaRepository<Meals, String>{

	List<Meals> findByPlanId(String id);
	
	Meals findByMealId(String id);

	

}
