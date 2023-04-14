package com.incedo.service;

import java.util.Date;
import java.util.List;

public interface AdminInterface {
	// probably return a collection of courses and grades
	/**
	 * takes finds the admin in list of admins
	 * @param username
	 * @param password
	 * @return
	 */
	public int findAdminId(String username, String password);

	/**
	 * generates report card for student
	 * @param userId
	 * @param studentId
	 * @param grades
	 */
	public void generateReportCard(int userId, int studentId, String grades);

	/**
	 * adds the professor
	 * @param name
	 * @param username
	 * @param password
	 * @param address
	 * @param department
	 * @param designation
	 * @param datep
	 */
	public void addProfessor(String name, String username, String password, String address, String department, String designation, Date datep);

	/**
	 * approves a student
	 * @param studentid
	 */
	public void approveStudentRegistration(int studentid);
	
	/**
	 * add professor id to course
	 * @param courseCode
	 * @param professorid
	 * @return
	 */
	public int addProfessorToCourse(String courseCode, int professorid);

	/**
	 * create a new course
	 * @param courseCode
	 * @param name
	 * @param isOffered
	 * @param professorid
	 * @return
	 */
	public boolean addCourse(String courseCode, String name, boolean isOffered, int professorid);

	/**
	 * removes a course
	 * @param courseCode
	 * @return
	 */
	public boolean removeCourse(String courseCode);

	/**
	 * changes password of admin
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean passwordChange(int userId, String password);
	
	/**
	 * changes whether a student is registered
	 */
	public void registerStudent();
	
	/**
	 * returns list of unapproved students
	 * @return
	 */
	public List<String> listOfUnapproved();
	
	/**
	 * approves all unapproved students
	 */
	public void approveAll();
}
