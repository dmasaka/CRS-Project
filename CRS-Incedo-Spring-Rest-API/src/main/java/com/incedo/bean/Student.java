package com.incedo.bean;

public class Student extends User {
	
	private int studentId;
	private int semesterId;
	private String branch;
	private boolean isApproved;
	
//	public Student() {};
	public Student(int userId, String name, String username, String password, String address, int studentId, int semesterId, String branch) {
		setUserId(userId);
		setName(name);
		setUsername(username);
		setPassword(password);
		setAddress(address);
		setStudentId(studentId);
		setSemesterId(semesterId);
		setBranch(branch);
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	}
}
