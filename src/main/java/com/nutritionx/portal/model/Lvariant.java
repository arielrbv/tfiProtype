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
@Table(name = "lvariant")
public class Lvariant {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;

	
	@ManyToMany(mappedBy = "lVariant", cascade = CascadeType.ALL)
	private Set<Lunch> lunch = new HashSet<>();
	
	
	public Lvariant() {
		// TODO Auto-generated constructor stub
	}
	public Lvariant(String id, String description) {
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
//	public Set<Lunch> getLunch() {
//		return lunch;
//	}
	public void setLunch(Set<Lunch> l) {
		this.lunch = l;
	}
	public void addLunch(Lunch l) {
		this.lunch.add(l);
	}
	

	@Override
	public String toString() {
		return "Lvariant [id=" + id + ", description=" + description + "]";
	}
		
}
