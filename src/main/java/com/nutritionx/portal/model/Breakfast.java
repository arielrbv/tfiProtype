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
@Table(name = "breakfast")
public class Breakfast {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	@Column(name = "id")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;
	
	
	/**RELATIONSHIPS WITH TABLES*/
	//WITH corresponding Variant
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			  name = "breakfast_variants", 
			  joinColumns = @JoinColumn(name = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "variant_id")
			  )
	Set<Bvariant> bVariant = new HashSet<>();
	
	
	public Breakfast() {
	}
	public Breakfast(String id, String description) {
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
	public Set<Bvariant> getbVariant() {
		return bVariant;
	}
	public void setbVariant(Set<Bvariant> bVariant) {
		this.bVariant = bVariant;
	}
	public void addVariant(Bvariant bv) {
		this.bVariant.add(bv);
	}
	
	
	
	@Override
	public String toString() {
		return "Breakfast [id=" + id + ", description=" + description + "]";
	}
	
}
