package com.incedo.bean;

import java.util.Date;

public class Admin extends User {
	
	
	private Date doj;
	
	
	/**
	 * constructor for admin
	 * @param userId
	 * @param name
	 * @param username
	 * @param password
	 * @param address
	 * @param doj
	 */
	public Admin(int userId, String name, String username, String password, String address, Date doj) {
		setUserId(userId);
		setName(name);
		setUsername(username);
		setPassword(password);
		setAddress(address);
		this.doj = doj;
	}

	/** getter for date
	 * @return Date
	 */
	public Date getDoj() {
		return doj;
	}

	/** setter for date
	 * @param doj
	 */
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
}
