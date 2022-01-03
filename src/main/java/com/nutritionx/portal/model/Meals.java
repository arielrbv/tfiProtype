package com.nutritionx.portal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "meals")
public class Meals {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	@Column(name = "meal_id")
	private String mealId;
	@Column(name = "plan_id", insertable = false, updatable = false)
	private String planId;
	@Column(name="day")
	private Integer day;


	@Column(name="breakfast_id")
	private String breakfastId;
	@Column(name="breakfast_description", columnDefinition = "TEXT")
	private String breakfastDescription;
	@Column(name="msnack_id")
	private String msnackId;
	@Column(name="msnack_description", columnDefinition = "TEXT")
	private String msnackDescription;
	@Column(name="lunch_id")
	private String lunchId;
	@Column(name="lunch_description", columnDefinition = "TEXT")
	private String lunchDescription;
	@Column(name="asnack_id")
	private String asnackId;
	@Column(name="asnack_description", columnDefinition = "TEXT")
	private String asnackDescription;
	@Column(name="pdsnack_id")
	private String pdsnackId;
	@Column(name="pdsnack_description", columnDefinition = "TEXT")
	private String pdsnackDescription;
	@Column(name="dinner_id")
	private String dinnerId;
	@Column(name="dinner_description", columnDefinition = "TEXT")
	private String dinnerDescription;
	
	/**RELATIONSHIPS WITH TABLES*/
	//WITH NUTRITIONAL_PLAN
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="plan_Id")
	private NutritionalPlan nutriPlan;

	
	
	public Meals() {
		// TODO Auto-generated constructor stub
	}
	public Meals(String mealId, String planId, Integer day, String breakfastId, String msnackId, String lunchId,
			String asnackId, String pdsnackId, String dinnerId, NutritionalPlan nutriPlan) {
		this.mealId = mealId;
		this.planId = planId;
		this.day = day;
		this.breakfastId = breakfastId;
		this.msnackId = msnackId;
		this.lunchId = lunchId;
		this.asnackId = asnackId;
		this.pdsnackId = pdsnackId;
		this.dinnerId = dinnerId;
		this.nutriPlan = nutriPlan;
	}
	public String getMealId() {
		return mealId;
	}
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getBreakfastId() {
		return breakfastId;
	}
	public void setBreakfastId(String breakfastId) {
		this.breakfastId = breakfastId;
	}
	public String getBreakfastDescription() {
		return breakfastDescription;
	}
	public void setBreakfastDescription(String breakfastDescription) {
		this.breakfastDescription = breakfastDescription;
	}
	public String getMsnackId() {
		return msnackId;
	}
	public void setMsnackId(String msnackId) {
		this.msnackId = msnackId;
	}
	public String getMsnackDescription() {
		return msnackDescription;
	}
	public void setMsnackDescription(String msnackDescription) {
		this.msnackDescription = msnackDescription;
	}
	public String getLunchId() {
		return lunchId;
	}
	public void setLunchId(String lunchId) {
		this.lunchId = lunchId;
	}
	public String getLunchDescription() {
		return lunchDescription;
	}
	public void setLunchDescription(String lunchDescription) {
		this.lunchDescription = lunchDescription;
	}
	public String getAsnackId() {
		return asnackId;
	}
	public void setAsnackId(String asnackId) {
		this.asnackId = asnackId;
	}
	public String getAsnackDescription() {
		return asnackDescription;
	}
	public void setAsnackDescription(String asnackDescription) {
		this.asnackDescription = asnackDescription;
	}
	public String getPdsnackId() {
		return pdsnackId;
	}
	public void setPdsnackId(String pdsnackId) {
		this.pdsnackId = pdsnackId;
	}
	public String getPdsnackDescription() {
		return pdsnackDescription;
	}
	public void setPdsnackDescription(String pdsnackDescription) {
		this.pdsnackDescription = pdsnackDescription;
	}
	public String getDinnerId() {
		return dinnerId;
	}
	public void setDinnerId(String dinnerId) {
		this.dinnerId = dinnerId;
	}
	public String getDinnerDescription() {
		return dinnerDescription;
	}
	public void setDinnerDescription(String dinnerDescription) {
		this.dinnerDescription = dinnerDescription;
	}
	




			
}
