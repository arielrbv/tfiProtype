package com.nutritionx.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.NutritionalPlan;

public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, String>{

	NutritionalPlan findByNutriPlanId(String id);
}
