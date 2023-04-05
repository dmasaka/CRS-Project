package com.incedo.bean;

import java.util.Date;

public class Admin extends User {
	
	private Date doj;
	
	
	public Admin(int userId, String name, String username, String password, String address, Date doj) {
		setUserId(userId);
		setName(name);
		setUsername(username);
		setPassword(password);
		setAddress(address);
		this.doj = doj;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
}
