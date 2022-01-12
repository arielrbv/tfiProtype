package com.nutritionx.portal.util;

public class PatientPatologiesSelected {
	
	private String name;
	private boolean selected;
	
	public PatientPatologiesSelected() {
		// TODO Auto-generated constructor stub
	}

	public PatientPatologiesSelected(String name, boolean selected) {
		this.name = name;
		this.selected = selected;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

}
