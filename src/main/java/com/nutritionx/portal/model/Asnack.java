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
@Table(name = "asnack")
public class Asnack {
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
			  name = "asnack_variants", 
			  joinColumns = @JoinColumn(name = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "variant_id")
			  )
	Set<Asvariant> asVariant = new HashSet<>();
	
	
	public Asnack() {
	}
	public Asnack(String id, String description) {
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
	public Set<Asvariant> getAsVariant() {
		return asVariant;
	}
	public void setAsVariant(Set<Asvariant> asVariant) {
		this.asVariant = asVariant;
	}
	public void addAsnack(Asvariant as) {
		this.asVariant.add(as);
	}
	
	
	@Override
	public String toString() {
		return "Asnack [id=" + id + ", description=" + description + "]";
	}
	

}
