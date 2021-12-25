package com.nutritionx.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutritionx.portal.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String>{
	//for access the CRUD methods and others
	
	//fin patient by id
	Patient findByPatientId(String id);

	//Find Patiens by email, will return 1 object only.
	Patient findByEmail(String email);

	//Find Patiens by email, will return 1 object only.
	Patient findByEmailAndPassword(String email, String Password);
	
}
