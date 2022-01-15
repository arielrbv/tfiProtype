package com.nutritionx.portal.util;

import com.nutritionx.portal.model.Patient;

public class PatientForList {
	private Patient p;
	private String avatar;
	
	public PatientForList() {
		// TODO Auto-generated constructor stub
	}

	public PatientForList(Patient p, String avatar) {
		this.p = p;
		this.avatar = avatar;
	}
	public Patient getP() {
		return p;
	}
	public void setP(Patient p) {
		this.p = p;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	

}
