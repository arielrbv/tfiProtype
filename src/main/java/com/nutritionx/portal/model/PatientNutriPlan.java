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
	private Integer day;
	@Column(columnDefinition="TEXT")
	private String breakfast;
	@Column(columnDefinition="TEXT")
	private String msnack;
	@Column(columnDefinition="TEXT")
	private String lunch;
	@Column(columnDefinition="TEXT")
	private String asnack;
	@Column(columnDefinition="TEXT")
	private String pdsnack;
	@Column(columnDefinition="TEXT")
	private String dinner;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="patient_id")
	private Patient patient;
	
	
	public PatientNutriPlan() {
		// TODO Auto-generated constructor stub
	}
	public PatientNutriPlan(Integer id, String planId, String planType, Integer day, String breakfast, String msnack,
			String lunch, String asnack, String pdsnack, String dinner) {
		this.id = id;
		this.planId = planId;
		this.planType = planType;
		this.day = day;
		this.breakfast = breakfast;
		this.msnack = msnack;
		this.lunch = lunch;
		this.asnack = asnack;
		this.pdsnack = pdsnack;
		this.dinner = dinner;
	}
	
	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getMsnack() {
		return msnack;
	}
	public void setMsnack(String msnack) {
		this.msnack = msnack;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getAsnack() {
		return asnack;
	}
	public void setAsnack(String asnack) {
		this.asnack = asnack;
	}
	public String getPdsnack() {
		return pdsnack;
	}
	public void setPdsnack(String pdsnack) {
		this.pdsnack = pdsnack;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
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
				+ ", breakfast=" + breakfast + ", msnack=" + msnack + ", lunch=" + lunch + ", asnack=" + asnack
				+ ", pdsnack=" + pdsnack + ", dinner=" + dinner + "]";
	}
	
}
