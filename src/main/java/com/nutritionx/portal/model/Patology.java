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
@Table(name = "patology")
public class Patology {
	@Id
	@Column(name = "patology_id") //--> only used when the name of the table field is different.
	private String patologyId;
	private String name;
	private String description;
	
	
	
	@ManyToMany(mappedBy = "patologies", cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<>();
	
	
	public Patology() {
		// TODO Auto-generated constructor stub
	}
	public Patology(String patologyId, String name, String description) {
		this.patologyId = patologyId;
		this.name = name;
		this.description = description;
	}



	public String getPatologyId() {
		return patologyId;
	}
	public void setPatologyId(String patologyId) {
		this.patologyId = patologyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addPatient(Patient p) {
		this.patients.add(p);
	}
	
	
	
	@Override
	public String toString() {
		return "Patology [patologyId=" + patologyId + ", name=" + name + ", description=" + description + "]";
	}
	
}
