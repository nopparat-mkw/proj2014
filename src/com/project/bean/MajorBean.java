package com.project.bean;

public class MajorBean 
{
	private String majorName;
	private EducationLevelBean educationLevel;

	public MajorBean(String majorName) {
		super();
		this.majorName = majorName;
	}

	public MajorBean() {
		super();
	}

	public String getMajorName() {
		return majorName;
	}

	public EducationLevelBean getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevelBean educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
}
