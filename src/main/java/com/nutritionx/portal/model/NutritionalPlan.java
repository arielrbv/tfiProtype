package com.nutritionx.portal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "nutritional_plan")
public class NutritionalPlan {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	@Column(name = "nutriplan_id")
	private String nutriPlanId;
	private String name;
	private int status;
	
	
	/**RELATIONSHIPS WITH TABLES*/
	//WITH MEALS
	@OneToMany(mappedBy = "nutriPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Meals> mealsOfplan = new HashSet<>();
	
	
	public NutritionalPlan() {
		// TODO Auto-generated constructor stub
	}
	public NutritionalPlan(String nutriPlanId, String name, int status) {
		this.nutriPlanId = nutriPlanId;
		this.name = name;
		this.status = status;
	}
	

	public String getNutriPlanId() {
		return nutriPlanId;
	}
	public void setNutriPlanId(String nutriPlanId) {
		this.nutriPlanId = nutriPlanId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Set<Meals> getMealsOfplan() {
		return mealsOfplan;
	}
	public void setMealsOfplan(Set<Meals> mealsOfplan) {
		this.mealsOfplan = mealsOfplan;
	}
	public void addMealsofPlan(Meals mealOfPlan) {
		this.mealsOfplan.add(mealOfPlan);
	}

	
	@Override
	public String toString() {
		return "NutritionalPlan [nutriPlanId=" + nutriPlanId + ", name=" + name + ", status=" + status + "]";
	}
}
