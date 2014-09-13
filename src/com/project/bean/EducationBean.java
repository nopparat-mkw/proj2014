package com.project.bean;


public class EducationBean {
	private String educationalInstitution;
	private String educationalBackground;
	private String educationalMajor;

	public EducationBean() {
		super();
	}

	public EducationBean(String educationalInstitution,
			String educationalBackground, String educationalMajor) {
		super();
		this.educationalInstitution = educationalInstitution;
		this.educationalBackground = educationalBackground;
		this.educationalMajor = educationalMajor;
	}

	public String getEducationalInstitution() {
		return educationalInstitution;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public String getEducationalMajor() {
		return educationalMajor;
	}

	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public void setEducationalMajor(String educationalMajor) {
		this.educationalMajor = educationalMajor;
	}
	

}
