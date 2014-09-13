package com.project.bean;

public class LetterBean {
	private ScheduleBean schedule;

	public LetterBean() {

	}

	public ScheduleBean getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleBean schedule) {
		this.schedule = schedule;
	}

	public LetterBean(ScheduleBean schedule) {
		super();
		this.schedule = schedule;
	}

}
