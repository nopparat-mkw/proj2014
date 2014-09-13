package com.project.bean;

public class EducationLevelBean {
	private int educationLevel;
	private String educationalBackground;
	private StudentBean student;

	public EducationLevelBean() {

	}

	public EducationLevelBean(int educationLevel, String educationalBackground,
			StudentBean student) {
		super();
		this.educationLevel = educationLevel;
		this.educationalBackground = educationalBackground;
		this.student = student;
	}

	public int getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(int educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public StudentBean getStudent() {
		return student;
	}

	public void setStudent(StudentBean student) {
		this.student = student;
	}

}
