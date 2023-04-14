/**
 * 
 */
package com.incedo.dao;

import java.util.List;

import com.incedo.bean.Course;

/**
 * @author David Masaka
 *
 */
public interface CourseDAOInterface {
	/**
	 * list all courses
	 * @return list of courses
	 */
	public List<Course> listOfCourses();
	/**
	 * adds a course
	 * @param course
	 */
	public void addCourse(Course course);
	/**
	 * deletes a course
	 * @param courseCode
	 */
	public void deleteCourse(String courseCode);
	/**
	 * adds professor
	 * @param courseCode
	 * @param professorid
	 * @return
	 */
	public int addProfessor(String courseCode, int professorid);
}
