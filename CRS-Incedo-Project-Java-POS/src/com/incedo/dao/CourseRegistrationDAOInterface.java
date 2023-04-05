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
	/**
	 * list all courses
	 * @return
	 */
	public List<CourseRegistration> listOfCourses();
	/**
	 * list all courses a student is taking
	 * @param studentid
	 * @return
	 */
	public List<CourseRegistration> listOfCoursesByStudentId(int studentid);
	/**
	 * list all courses a professor is teaching
	 * @param professorid
	 * @return
	 */
	public List<CourseRegistration> listOfCoursesByProfessorId(int professorid);
	/**
	 * changes the grades of a student
	 * @param courseCode
	 * @param studentid
	 * @param grade
	 */
	public void changeGrade(String courseCode, int studentid, String grade);
	/**
	 * add student to a course
	 * @param studentid
	 * @param courseCode
	 */
	public void addCourseRegistration(int studentid, String courseCode);
	/**
	 * removes student from a course
	 * @param studentid
	 * @param courseCode
	 */
	public void deleteCourseRegistration(int studentid, String courseCode);
}
