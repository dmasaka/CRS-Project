package com.incedo.dao;

import java.util.List;

import com.incedo.bean.Student;
import com.incedo.exception.ApprovalNotDone;
import com.incedo.exception.UserNotFoundException;

public interface StudentDAOInterface {
	/**
	 * lists all students
	 * @return
	 */
	public List<Student> listOfStudents();
	/**
	 * creates new student
	 * @param stud
	 */
	public void add(Student stud);
	/**
	 * login for students
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 * @throws ApprovalNotDone
	 */
	public Student login(String username, String password);
	/**
	 * lists unproved students
	 * @return
	 */
	public List<Student> listOfUnapprovedStudents();
	/**
	 * approves all unproved students
	 */
	public void approveAll();
}
