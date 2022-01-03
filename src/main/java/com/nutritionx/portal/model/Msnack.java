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
@Table(name = "msnack")
public class Msnack {
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
			  name = "msnack_variants", 
			  joinColumns = @JoinColumn(name = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "variant_id")
			  )
	Set<Msvariant> msVariant = new HashSet<>();
	
	
	public Msnack() {
	}
	public Msnack(String id, String description) {
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
	public Set<Msvariant> getMsVariant() {
		return msVariant;
	}
	public void setMsVariant(Set<Msvariant> msVariant) {
		this.msVariant = msVariant;
	}
	public void addMSnack(Msvariant ms) {
		this.msVariant.add(ms);
	}
	
	
	@Override
	public String toString() {
		return "Msnack [id=" + id + ", description=" + description + "]";
	}
	

}
