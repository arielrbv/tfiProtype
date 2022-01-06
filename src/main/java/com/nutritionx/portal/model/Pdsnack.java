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
@Table(name = "pdsnack")
public class Pdsnack {
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
			  name = "pdsnack_variants", 
			  joinColumns = @JoinColumn(name = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "variant_id")
			  )
	Set<Pdsvariant> pdsVariant = new HashSet<>();
	
	
	public Pdsnack() {
	}
	public Pdsnack(String id, String description) {
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
	public Set<Pdsvariant> getPdsVariant() {
		return pdsVariant;
	}
	public void setPdsVariant(Set<Pdsvariant> pdsv) {
		this.pdsVariant = pdsv;
	}
	public void addPdSnack(Pdsvariant pdsv) {
		this.pdsVariant.add(pdsv);
	}
	
	
	@Override
	public String toString() {
		return "Pdsnack [id=" + id + ", description=" + description + "]";
	}
	

}
