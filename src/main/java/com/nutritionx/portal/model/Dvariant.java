package com.nutritionx.portal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dvariant")
public class Dvariant {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;

	
	@ManyToMany(mappedBy = "dVariant", cascade = CascadeType.ALL)
	private Set<Dinner> dinner = new HashSet<>();
	
	
	public Dvariant() {
		// TODO Auto-generated constructor stub
	}
	public Dvariant(String id, String description) {
		this.id = id;
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public Set<Dinner> getLunch() {
//		return dinner;
//	}
	public void setBreakfast(Set<Dinner> d) {
		this.dinner = d;
	}
	public void addBreakfast(Dinner d) {
		this.dinner.add(d);
	}
	

	@Override
	public String toString() {
		return "Dvariant [id=" + id + ", description=" + description + "]";
	}
		
}
