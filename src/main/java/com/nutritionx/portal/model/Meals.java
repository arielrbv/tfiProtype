package com.nutritionx.portal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
//	@Column(name = "plan_id",insertable  = false, updatable = false)
//	private String planId;
	
	//WITH NUTRITIONAL_PLAN
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="plan_Id")
	private NutritionalPlan nutriPlan;
	
	@Column(name="day")
	private Integer day;
	@OneToOne
	@JoinColumn(name = "breakfast_id")
	@MapsId
	private Breakfast breakfast;
	@OneToOne
	@JoinColumn(name = "msnack_id")
	@MapsId
	private Msnack mSnack;
	@OneToOne
	@JoinColumn(name = "lunch_id")
	@MapsId
	private Lunch lunch;
	@OneToOne
	@JoinColumn(name = "asnack_id")
	@MapsId
	private Asnack aSnack;
	@OneToOne
	@JoinColumn(name = "pdsnack_id")
	@MapsId
	private Pdsnack pdSnack;
	@OneToOne
	@JoinColumn(name = "dinner_id")
	@MapsId
	private Dinner dinner;
//	@Column(name="msnack_id")
//	private String msnackId;	
//	@Column(name="lunch_id")
//	private String lunchId;
//	@Column(name="asnack_id")
//	private String asnackId;
//	@Column(name="pdsnack_id")
//	private String pdsnackId;
//	@Column(name="dinner_id")
//	private String dinnerId;
	
	/**RELATIONSHIPS WITH TABLES*/
//	//WITH NUTRITIONAL_PLAN
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name ="plan_Id")
//	private NutritionalPlan nutriPlan;

	
	
	public Meals() {
		// TODO Auto-generated constructor stub
	}

	public Meals(String mealId, NutritionalPlan nutriPlan, Integer day, Breakfast breakfast, Msnack mSnack, Lunch lunch,
		Asnack aSnack, Pdsnack pdSnack, Dinner dinner) {
	this.mealId = mealId;
	this.nutriPlan = nutriPlan;
	this.day = day;
	this.breakfast = breakfast;
	this.mSnack = mSnack;
	this.lunch = lunch;
	this.aSnack = aSnack;
	this.pdSnack = pdSnack;
	this.dinner = dinner;
	}


	public String getMealId() {
		return mealId;
	}
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
//	public String getPlanId() {
//		return planId;
//	}
//	public void setPlanId(String planId) {
//		this.planId = planId;
//	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
//	public NutritionalPlan getNutriPlan() {
//		return nutriPlan;
//	}
	public void setNutriPlan(NutritionalPlan nutriPlan) {
		this.nutriPlan = nutriPlan;
	}
	public Breakfast getBreakfast() {
		return new Breakfast(breakfast.getId(), breakfast.getDescription());
	}
	public void setBreakfast(Breakfast breakfast) {
		this.breakfast = breakfast;
	}
	public Msnack getmSnack() {
		return new Msnack(mSnack.getId(), mSnack.getDescription());
	}
	public void setmSnack(Msnack mSnack) {
		this.mSnack = mSnack;
	}
	public Lunch getLunch() {
		return new Lunch(lunch.getId(), lunch.getDescription());
	}
	public void setLunch(Lunch lunch) {
		this.lunch = lunch;
	}
	public Asnack getaSnack() {
		return new Asnack(aSnack.getId(), aSnack.getDescription());
	}
	public void setaSnack(Asnack aSnack) {
		this.aSnack = aSnack;
	}
	public Pdsnack getPdSnack() {
		return new Pdsnack(pdSnack.getId(), pdSnack.getDescription());
	}
	public void setPdSnack(Pdsnack pdSnack) {
		this.pdSnack = pdSnack;
	}
	public Dinner getDinner() {
		return new Dinner(dinner.getId(), dinner.getDescription());
	}
	public void setDinner(Dinner dinner) {
		this.dinner = dinner;
	}

	@Override
	public String toString() {
		return "Meals [mealId=" + mealId + ", nutriPlan=" + nutriPlan.getName() + ", day=" + day + ", breakfast=" + breakfast.getDescription()
				+ ", mSnack=" + mSnack.getDescription() + ", lunch=" + lunch.getDescription() + ", aSnack=" + aSnack.getDescription() + ", pdSnack=" + pdSnack.getDescription()
				+ ", dinner=" + dinner.getDescription() + "]";
	}
			
}
