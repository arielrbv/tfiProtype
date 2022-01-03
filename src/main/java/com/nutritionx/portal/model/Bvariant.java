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
@Table(name = "bvariant")
public class Bvariant {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;

	
	@ManyToMany(mappedBy = "bVariant", cascade = CascadeType.ALL)
	private Set<Breakfast> breakfast = new HashSet<>();
	
	
	public Bvariant() {
		// TODO Auto-generated constructor stub
	}
	public Bvariant(String id, String description) {
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
//	public Set<Breakfast> getBreakfast() {
//		return breakfast;
//	}
	public void setBreakfast(Set<Breakfast> breakfast) {
		this.breakfast = breakfast;
	}
	public void addBreakfast(Breakfast b) {
		this.breakfast.add(b);
	}
	

	@Override
	public String toString() {
		return "Bvariant [id=" + id + ", description=" + description + "]";
	}
		
}
