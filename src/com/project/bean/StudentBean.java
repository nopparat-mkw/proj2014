package com.project.bean;

public class StudentBean extends PersonBean{
	private String studentID;
	ParentBean parent;

	public StudentBean() {

	}

	public StudentBean(String studentID, String antecedent, String firstName,
			String lastName) {
		super(antecedent, firstName, lastName);
		this.studentID = studentID;
	}

	public String getStudentID() {
		return studentID;
	}

	public ParentBean getParent() {
		return parent;
	}

	public void setParent(ParentBean parent) {
		this.parent = parent;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	
}
