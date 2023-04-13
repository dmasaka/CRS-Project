package com.incedo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String name;
	private String username;
	private String password;
	private String address;
	private Date doj;

	public Admin() {}

	/**
	 * constructor for admin
	 * @param userId
	 * @param name
	 * @param username
	 * @param password
	 * @param address
	 * @param doj
	 */
	public Admin(String name, String username, String password, String address, Date doj) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.doj = doj;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
	@Override
	public String toString() {
		return "Admin { name:" + name + ", username:" + username + " }";
	}
}
