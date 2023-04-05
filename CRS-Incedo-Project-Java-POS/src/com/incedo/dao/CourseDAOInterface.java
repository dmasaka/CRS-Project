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
	public List<Course> listOfCourses();
	public void addCourse(Course course);
	public void deleteCourse(String courseCode);
	public int addProfessor(String courseCode, int professorid);
}
