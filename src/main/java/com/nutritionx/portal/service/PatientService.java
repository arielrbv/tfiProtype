package com.nutritionx.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.repository.PatientRepository;

@Service
public class PatientService {
	//for CRUD logic
	
	@Autowired
	private PatientRepository patRep;
	
	public void createPatient(Patient p) {
		patRep.save(p);
	}

	
}
