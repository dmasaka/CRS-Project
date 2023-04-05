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
	
	public int findStudentId(String username, String password);
	
	public void registerForCourse(int studentId, String courseCode);
	
	//probably change the input to a student id and course
	public void addCourse(int studentId, String courseCode);
	
	//probably change the input to a student id and course
	public void deleteCourse(int studentId, String courseCode);

	//probably change the input to a student id and course
	public TreeMap<String, String> viewGrades(int studentId);
	
	public void addStudent(String name, String username, String password, String address);
	
	//probably change the input to a student id and course
//	public boolean payFee(int studentId);
}
