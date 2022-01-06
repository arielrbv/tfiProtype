package com.nutritionx.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Lunch;

public interface LunchRepository extends JpaRepository<Lunch, String>{

}
