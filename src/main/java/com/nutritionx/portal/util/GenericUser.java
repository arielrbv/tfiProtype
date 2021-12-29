package com.nutritionx.portal.util;

public class GenericUser {
	
	private String cPass;
	private String nPass;
	private String nPassC;
	
	public GenericUser() {
		// TODO Auto-generated constructor stub
	}

	public GenericUser(String cPass, String nPass, String nPassC) {
		this.cPass = cPass;
		this.nPass = nPass;
		this.nPassC = nPassC;
	}

	public String getcPass() {
		return cPass;
	}

	public void setcPass(String cPass) {
		this.cPass = cPass;
	}

	public String getnPass() {
		return nPass;
	}

	public void setnPass(String nPass) {
		this.nPass = nPass;
	}

	public String getnPassC() {
		return nPassC;
	}

	public void setnPassC(String nPassC) {
		this.nPassC = nPassC;
	}

	@Override
	public String toString() {
		return "User [cPass=" + cPass + ", nPass=" + nPass + ", nPassC=" + nPassC + "]";
	}
	
}
