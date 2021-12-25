package com.nutritionx.portal.model;

import java.util.List;

public class PatologyLists {
	
	private List<Patology> pats;
	
	public PatologyLists() {
		// TODO Auto-generated constructor stub
	}

	public PatologyLists(List<Patology> pats) {

		this.pats = pats;
	}

	public List<Patology> getPats() {
		return pats;
	}

	public void setPats(List<Patology> pats) {
		this.pats = pats;
	}

	@Override
	public String toString() {
		return "PatologyLists [pats=" + pats + "]";
	}
	
	
	
	
}
