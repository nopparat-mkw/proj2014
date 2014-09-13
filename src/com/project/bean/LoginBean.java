package com.project.bean;

public class LoginBean {
	private String username;
	private String password;
	private String status;

	public LoginBean() {

	}

	public LoginBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginBean(String username, String password, String status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
