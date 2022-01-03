package com.nutritionx.portal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "dinner")
public class Dinner {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;
	
	
	/**RELATIONSHIPS WITH TABLES*/
	//WITH corresponding Variant
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			  name = "dinner_variants", 
			  joinColumns = @JoinColumn(name = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "variant_id")
			  )
	Set<Dvariant> dVariant = new HashSet<>();
	
	
	public Dinner() {
	}
	public Dinner(String id, String description) {
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
	public Set<Dvariant> getdVariant() {
		return dVariant;
	}
	public void setdVariant(Set<Dvariant> dVariant) {
		this.dVariant = dVariant;
	}
	public void addDVariant(Dvariant dv) {
		this.dVariant.add(dv);
	}
	
	
	
	@Override
	public String toString() {
		return "Dinner [id=" + id + ", description=" + description + "]";
	}
	
}
