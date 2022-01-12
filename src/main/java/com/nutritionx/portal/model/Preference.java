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
	private String name;
	private String type;
	private String description;
	

	
	@ManyToMany(mappedBy = "preferences", cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<>();

	
	
	public Preference() {
		// TODO Auto-generated constructor stub
	}

	public Preference(String preferenceId, String name, String type, String description) {
		this.preferenceId = preferenceId;
		this.name = name;
		this.type = type;
		this.description = description;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Patient> getPatients() {
		return patients;
	}
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
	public void addPatient(Patient p) {
		this.patients.add(p);
	}

	
	@Override
	public String toString() {
		return "Preference [preferenceId=" + preferenceId + ", name=" + name + ", type=" + type + ", description=" + description
				+ "]";
	}

	
	

}