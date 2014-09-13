package com.project.bean;


public class HolidayBean {
	private int date,month,year;
	private String detail,type;
	
	public HolidayBean(int date, int month, int year, String detail) {
		this.date = date;
		this.month = month;
		this.year = year;
		this.detail = detail;
	}
	
	public HolidayBean(){}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
