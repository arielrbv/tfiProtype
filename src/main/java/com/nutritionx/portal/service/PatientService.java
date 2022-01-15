package com.nutritionx.portal.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.util.PatientForList;

@Service
public class PatientService {
	// for CRUD logic
	@Autowired
	private PatientRepository patRep;

	// patient creation
	public void createPatient(Patient p) {
		patRep.save(p);
	}

	// patient update (same as the other but meh.. to differentiate them)
	public void updatePatient(Patient p) {
		patRep.save(p);
	}

	
	public Page<PatientForList> findPaginated(List<PatientForList> p, Pageable pageable) {
		int pageSize = 6;
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<PatientForList> patL = p;

		if (p.size() < startItem) {
			patL = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, p.size());
            patL = p.subList(startItem, toIndex);
        }

        Page<PatientForList> patientPage
          = new PageImpl<PatientForList>(patL, PageRequest.of(currentPage, pageSize), p.size());

        return patientPage;
	}
	
}
