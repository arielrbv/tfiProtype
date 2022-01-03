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
@Table(name = "asvariant")
public class Asvariant {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;

	
	@ManyToMany(mappedBy = "asVariant", cascade = CascadeType.ALL)
	private Set<Asnack> aSnack = new HashSet<>();
	
	
	public Asvariant() {
		// TODO Auto-generated constructor stub
	}
	public Asvariant(String id, String description) {
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
//	public Set<Asnack> getaSnack() {
//		return aSnack;
//	}
	public void setmSnack(Set<Asnack> aSnack) {
		this.aSnack = aSnack;
	}
	public void addAsnack(Asnack as) {
		this.aSnack.add(as);
	}
	

	@Override
	public String toString() {
		return "Asvariant [id=" + id + ", description=" + description + "]";
	}
}
