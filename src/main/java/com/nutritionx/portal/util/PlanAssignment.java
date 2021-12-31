package com.nutritionx.portal.util;

public class PlanAssignment {
	private String gender;
	private Integer age;
	private float weight;
	private float height;
	private String exerciseTime;
	private String assigment;
	
	public PlanAssignment() {
		// TODO Auto-generated constructor stub
	}

	public PlanAssignment(String gender, Integer age, float weight, float height, String exerciseTime, String assigment) {
		this.gender = gender;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.exerciseTime = exerciseTime;
		this.assigment = assigment;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getExerciseTime() {
		return exerciseTime;
	}

	public void setExerciseTime(String exerciseTime) {
		this.exerciseTime = exerciseTime;
	}
	
	public String getAssigment() {
		return assigment;
	}
	
	public void setAssigment(String assigment) {
		this.assigment = assigment;
	}


	@Override
	public String toString() {
		return "NutriPlan [gender=" + gender + ", age=" + age + ", weight=" + weight + ", height=" + height
				+ ", exerciseTime=" + exerciseTime + ", assigment=" + assigment + "]";
	}
}
