/**
 * 
 */
package com.incedo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.bean.CourseRegistration;
import com.incedo.bean.Professor;
import com.incedo.service.ProfessorServiceOperation;

/**
 * @author David Masaka
 *
 */
@RestController
public class ProfessorController {
	@Autowired
	private ProfessorServiceOperation profserve;
	
	/**
	 * it finds professor with username and password
	 * @param username
	 * @param password
	 * @return professor
	 */
	@RequestMapping(value = "/professor/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Professor login(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("username: " + username + ", password: " + password);
		return profserve.findProfessorId(username, password);
	}
	
	/**
	 * lets a professor view all their students
	 * @param professorid
	 * @return list of course registrations
	 */
	@RequestMapping(value = "/professor/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> viewStudents(@PathVariable("id") int professorid){
		return profserve.viewStudents(professorid);
	}
	
	/**
	 * changes student grade
	 * @param professorid
	 * @param courseCode
	 * @param studentid
	 * @param grade
	 * @return boolean
	 */
	@RequestMapping(value = "/professor/grade", method = RequestMethod.POST, consumes = "application/json")
	public boolean addGrade(@RequestParam("professorid") int professorid, @RequestParam("courseCode") String courseCode, @RequestParam("studentid") int studentid, @RequestParam("grade") String grade) {
		profserve.addGrade(professorid, courseCode, studentid, grade);
		return true;
	}
}
