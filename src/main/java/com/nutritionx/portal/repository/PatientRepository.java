package com.nutritionx.portal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutritionx.portal.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String>{

	Patient findByPatientId(String id);

	Patient findByEmail(String email);

	Patient findByEmailAndPassword(String email, String Password);
	
	Patient findByEmailAndToken(String email, String token);
	
	Set<Patient> findByLastName(String lastName);
	
	
	
	
}
