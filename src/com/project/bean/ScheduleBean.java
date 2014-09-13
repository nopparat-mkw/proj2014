package com.project.bean;
import java.util.Date;

public class ScheduleBean {
//	private int schedule_ID;
	private Date dateAttendance;
	private String term;
	private AttendanceBean attendance;

	public ScheduleBean() {

	}

	public ScheduleBean(Date dateAttendance, String term, AttendanceBean attendance) {
		super();
		this.dateAttendance = dateAttendance;
		this.term = term;
		this.attendance = attendance;
	}

	public Date getDateAttendance() {
		return dateAttendance;
	}

	public void setDateAttendance(Date dateAttendance) {
		this.dateAttendance = dateAttendance;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public AttendanceBean getAttendance() {
		return attendance;
	}

	public void setAttendance(AttendanceBean attendance) {
		this.attendance = attendance;
	}

//	public int getSchedule_ID() {
//		return schedule_ID;
//	}
//
//	public void setSchedule_ID(int schedule_ID) {
//		this.schedule_ID = schedule_ID;
//	}

}
