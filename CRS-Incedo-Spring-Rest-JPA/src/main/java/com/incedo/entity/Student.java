package com.incedo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String name;
	private String username;
	private String password;
	private String address;
	private int semesterId;
	private String branch;
	private boolean isApproved;
	
	public Student() {}

	public Student(String name, String username, String password, String address,
			int semesterId, String branch, boolean isApproved) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.semesterId = semesterId;
		this.branch = branch;
		this.isApproved = isApproved;
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
	
	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	};
	
	public String toString() {
		return "Student { userid:" + getUserId() + ", name:" + name + ", username:" + username + "}";
	}
}
