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
	private Integer day;
	@Column(name = "breakfast_id")
	private String breakfastId;
	@Column(name = "breakfast_description", columnDefinition = "TEXT")
	private String breakfastDescription;
	@Column(name = "msnack_id")
	private String msnackId;
	@Column(name = "msnack_description", columnDefinition = "TEXT")
	private String msnackDescription;
	@Column(name = "lunch_id")
	private String lunchId;
	@Column(name = "lunch_description", columnDefinition = "TEXT")
	private String lunchDescription;
	@Column(name = "asnack_id")
	private String asnackId;
	@Column(name = "asnack_description", columnDefinition = "TEXT")
	private String asnackDescription;
	@Column(name = "pdsnack_id")
	private String pdsnackId;
	@Column(name = "pdsnack_description", columnDefinition = "TEXT")
	private String pdsnackDescription;
	@Column(name = "dinner_id")
	private String dinnerId;
	@Column(name = "dinner_description", columnDefinition = "TEXT")
	private String dinnerDescription;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public PatientNutriPlan() {
		// TODO Auto-generated constructor stub
	}

	public PatientNutriPlan(Integer id, String planId, Patient patient, Integer day, String breakfastId,
			String breakfastDescription, String msnackId, String msnackDescription, String lunchId,
			String lunchDescription, String asnackId, String asnackDescription, String pdsnackId,
			String pdsnackDescription, String dinnerId, String dinnerDescription) {
		this.id = id;
		this.planId = planId;
		this.patient = patient;
		this.day = day;
		this.breakfastId = breakfastId;
		this.breakfastDescription = breakfastDescription;
		this.msnackId = msnackId;
		this.msnackDescription = msnackDescription;
		this.lunchId = lunchId;
		this.lunchDescription = lunchDescription;
		this.asnackId = asnackId;
		this.asnackDescription = asnackDescription;
		this.pdsnackId = pdsnackId;
		this.pdsnackDescription = pdsnackDescription;
		this.dinnerId = dinnerId;
		this.dinnerDescription = dinnerDescription;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "PatientNutriPlan [id=" + id + ", planId=" + planId + ", day="
				+ day + ", breakfastId=" + breakfastId + ", breakfastDescription=" + breakfastDescription
				+ ", msnackId=" + msnackId + ", msnackDescription=" + msnackDescription + ", lunchId=" + lunchId
				+ ", lunchDescription=" + lunchDescription + ", asnackId=" + asnackId + ", asnackDescription="
				+ asnackDescription + ", pdsnackId=" + pdsnackId + ", pdsnackDescription=" + pdsnackDescription
				+ ", dinnerId=" + dinnerId + ", dinnerDescription=" + dinnerDescription + "]";
	}

}
