package com.nutritionx.portal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "preference")
public class Preference{
	

	@Id
	@Column(name = "preference_id")
	private String preferenceId;
	@Column(name = "name")
	private String name;
	

	
	@ManyToMany(mappedBy = "preferences", cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<>();

	
	
	public Preference() {
		// TODO Auto-generated constructor stub
	}
	public Preference(String preferenceId, String name) {
		this.preferenceId = preferenceId;
		this.name = name;
	}
	
	
	public String getPreferenceId() {
		return preferenceId;
	}
	public void setPreferenceId(String preferenceId) {
		this.preferenceId = preferenceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addPatient(Patient p) {
		this.patients.add(p);
	}

	
	
	@Override
	public String toString() {
		return "PreferenceLib [preferenceId=" + preferenceId + ", name=" + name + ", patients=" + patients + "]";
	}
}