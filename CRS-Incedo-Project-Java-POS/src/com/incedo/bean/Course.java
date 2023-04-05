package com.incedo.bean;

public class Course {
	
	private String courseCode;
	private String name;
	private boolean isOffered;
	private int professorId;
	public Course() {};
	public Course(String courseCode, String name, boolean isOffered, int professorId) {
		this.courseCode = courseCode;
		this.name = name;
		this.isOffered = isOffered;
		this.professorId = professorId;
	}

	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOffered() {
		return isOffered;
	}
	public void setOffered(boolean isOffered) {
		this.isOffered = isOffered;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
}
