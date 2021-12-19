package com.nutritionx.portal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_nutriplan")
public class PatientNutriPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "plan_id")
	private String planId;
	@Column(name = "plan_type",length = 45)
	private String planType;
	@Column(length = 45)
	private String day;
	@Column(name = "daily_meal",length = 45)
	private String dailyMeal;
	private String detail;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="patient_id")
	private Patient patient;
	
	
	
	public PatientNutriPlan() {
		// TODO Auto-generated constructor stub
	}
	public PatientNutriPlan(String planId, String planType, String day, String dailyMeal,
			String detail) {
		this.planId = planId;
		this.planType = planType;
		this.day = day;
		this.dailyMeal = dailyMeal;
		this.detail = detail;
	}
	
	
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDailyMeal() {
		return dailyMeal;
	}
	public void setDailyMeal(String dailyMeal) {
		this.dailyMeal = dailyMeal;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "PatientNutriPlan [id=" + id + ", planId=" + planId + ", planType=" + planType + ", day=" + day
				+ ", dailyMeal=" + dailyMeal + ", detail=" + detail + ", patient=" + patient + "]";
	}
	

	
}
