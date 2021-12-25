package com.nutritionx.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nutritionx.portal.model.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, String> {
	
	//find preference by id
	Preference findByPreferenceId(String prefereceId); 
	//find preference that have null description
	@Query(value = "SELECT p FROM Preference p WHERE p.description is null")
	List<Preference> findAllAndDescriptionisNull();
}
