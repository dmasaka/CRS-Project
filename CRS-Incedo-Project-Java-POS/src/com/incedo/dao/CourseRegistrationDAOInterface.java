/**
 * 
 */
package com.incedo.dao;

import java.util.List;

import com.incedo.bean.CourseRegistration;

/**
 * @author David Masaka
 *
 */
public interface CourseRegistrationDAOInterface {
	public List<CourseRegistration> listOfCourses();
	public List<CourseRegistration> listOfCoursesByStudentId(int studentid);
	public List<CourseRegistration> listOfCoursesByProfessorId(int professorid);
	public void changeGrade(String courseCode, int studentid, String grade);
	public void addCourseRegistration(int studentid, String courseCode);
	public void deleteCourseRegistration(int studentid, String courseCode);
}
