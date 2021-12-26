package com.nutritionx.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.PatientNutriPlan;

public interface PatientNutriPlanRepository extends JpaRepository<PatientNutriPlan, Integer>{
	//findBypatientIdOrderByDayAsc
	List<PatientNutriPlan> findByPatientOrderByDayAsc(Patient patient);

}
