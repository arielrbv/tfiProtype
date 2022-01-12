package com.nutritionx.portal.util;

public class PatientPreferencesSelected {

	private String name;
	private String type;
	private boolean selected;
	
	public PatientPreferencesSelected() {
		// TODO Auto-generated constructor stub
	}

	public PatientPreferencesSelected(String name,String type, boolean selected) {
		this.name = name;
		this.type = type;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
		
	
}
