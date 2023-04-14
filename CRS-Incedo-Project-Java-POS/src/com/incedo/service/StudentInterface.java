/**
 * 
 */
package com.incedo.service;

import java.util.TreeMap;

/**
 * @author David Masaka
 *
 */
public interface StudentInterface {
	
	/**
	 * finds student in a list of students
	 * @param username
	 * @param password
	 * @return
	 */
	public int findStudentId(String username, String password);
	
	/**
	 * registers the student for a school
	 * @param studentId
	 * @param courseCode
	 */
	public void registerForCourse(int studentId, String courseCode);
	
	//probably change the input to a student id and course
	/**
	 * adds student to course
	 * @param studentId
	 * @param courseCode
	 */
	public void addCourse(int studentId, String courseCode);
	
	//probably change the input to a student id and course
	/**
	 * removes student from course
	 * @param studentId
	 * @param courseCode
	 */
	public void deleteCourse(int studentId, String courseCode);

	//probably change the input to a student id and course
	/**
	 * list the courses and the grades of the student
	 * @param studentId
	 * @return TreeMap<String, String>
	 */
	public TreeMap<String, String> viewGrades(int studentId);
	
	/**
	 * creates a new student
	 * @param name
	 * @param username
	 * @param password
	 * @param address
	 */
	public void addStudent(String name, String username, String password, String address);
	
	//probably change the input to a student id and course
//	public boolean payFee(int studentId);
}
