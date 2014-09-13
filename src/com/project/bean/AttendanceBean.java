package com.project.bean;
public class AttendanceBean {
	private String statusActivity;
	private StudentBean student;

	public AttendanceBean() {

	}

	public AttendanceBean(String statusActivity, StudentBean student) {
		super();
		this.statusActivity = statusActivity;
		this.student = student;
	}

	public String getStatusActivity() {
		return statusActivity;
	}

	public void setStatusActivity(String statusActivity) {
		this.statusActivity = statusActivity;
	}

	public StudentBean getStudent() {
		return student;
	}

	public void setStudent(StudentBean student) {
		this.student = student;
	}

}
