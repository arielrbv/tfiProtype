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
@Table(name = "pdsvariant")
public class Pdsvariant {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;

	
	@ManyToMany(mappedBy = "pdsVariant", cascade = CascadeType.ALL)
	private Set<Pdsnack> pdSnack = new HashSet<>();
	
	
	public Pdsvariant() {
		// TODO Auto-generated constructor stub
	}
	public Pdsvariant(String id, String description) {
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
//	public Set<Pdsnack> getPdSnack() {
//		return pdSnack;
//	}
	public void setPdSnack(Set<Pdsnack> pdSnack) {
		this.pdSnack = pdSnack;
	}
	public void addPdSnack(Pdsnack pds) {
		this.pdSnack.add(pds);
	}
	

	@Override
	public String toString() {
		return "Pdsvariant [id=" + id + ", description=" + description + "]";
	}
}
