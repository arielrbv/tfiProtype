package com.nutritionx.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritionx.portal.model.Dinner;

public interface DinnerRepository extends JpaRepository<Dinner, String>{

}
