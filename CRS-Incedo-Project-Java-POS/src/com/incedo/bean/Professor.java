package com.incedo.bean;

import java.sql.Date;

public class Professor extends User {
	
	private String department;
	private String designation;
	private Date doj;
	public Professor() {};
	public Professor(int userId, String name, String username, String password, String address, String department,
			String designation, Date doj) {
		setUserId(userId);
		setName(name);
		setUsername(username);
		setPassword(password);
		setAddress(address);
		this.department = department;
		this.designation = designation;
		this.doj = doj;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
}
