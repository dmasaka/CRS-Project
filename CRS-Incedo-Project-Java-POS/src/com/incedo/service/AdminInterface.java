package com.incedo.service;

import java.util.Date;
import java.util.List;

public interface AdminInterface {
	// probably return a collection of courses and grades
	public int findAdminId(String username, String password);

	public void generateReportCard(int userId, int studentId, String grades);

	public void addProfessor(String name, String username, String password, String address, String department, String designation, Date datep);

	public void approveStudentRegistration(int studentid);
	
	public int addProfessorToCourse(String courseCode, int professorid);

	public boolean addCourse(String courseCode, String name, boolean isOffered, int professorid);

	public boolean removeCourse(String courseCode);

	public boolean passwordChange(int userId, String password);
	
	public void registerStudent();
	
	public List<String> listOfUnapproved();
	
	public void approveAll();
}
