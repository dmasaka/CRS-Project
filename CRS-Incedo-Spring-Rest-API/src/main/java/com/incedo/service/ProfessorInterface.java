package com.incedo.service;

import java.util.List;

import com.incedo.bean.CourseRegistration;
import com.incedo.bean.Professor;

public interface ProfessorInterface {
	/**
	 * find professor from list of professors
	 * @param username
	 * @param password
	 * @return int
	 */
	public Professor findProfessorId(String username, String password);
	/**
	 * add student grade in class professor teaches
	 * @param professorId
	 * @param courseId
	 * @param studentid
	 * @param grade
	 * @return boolean
	 */
	public boolean addGrade(int professorId, String courseId, int studentid, String grade);
	/**
	 * list all the students the professor teaches
	 * @param professorId
	 * @return List<List<String>>
	 */
	public List<CourseRegistration> viewStudents(int professorId);
}
