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
@Table(name = "msvariant")
public class Msvariant {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	private String id;
	@Column(columnDefinition = "TEXT")
	private String description;

	
	@ManyToMany(mappedBy = "msVariant", cascade = CascadeType.ALL)
	private Set<Msnack> mSnack = new HashSet<>();
	
	
	public Msvariant() {
		// TODO Auto-generated constructor stub
	}
	public Msvariant(String id, String description) {
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
//	public Set<Msnack> getmSnack() {
//		return mSnack;
//	}
	public void setmSnack(Set<Msnack> mSnack) {
		this.mSnack = mSnack;
	}
	public void addMSnack(Msnack ms) {
		this.mSnack.add(ms);
	}
	

	@Override
	public String toString() {
		return "Msvariant [id=" + id + ", description=" + description + "]";
	}
}
