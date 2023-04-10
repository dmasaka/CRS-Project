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

import com.incedo.bean.Student;
import com.incedo.service.AdminServiceOperation;

/**
 * @author David Masaka
 *
 */
@RestController
public class AdminController {
	@Autowired
	private AdminServiceOperation adserve;
	
	/**
	 * returns the list of unapproved students
	 * @return List<Student>
	 */
	@RequestMapping(value = "/admin/unapproved", method = RequestMethod.GET, produces = "application/json")
	public List<Student> listUnapproved(){
		return adserve.listOfUnapproved();
	}
	
	/**
	 * adds a course that does not exist
	 * @param courseCode
	 * @param name
	 * @param isOffered
	 * @param professorid
	 * @return boolean
	 */
	@RequestMapping(value = "/admin/add/course", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public boolean addCourse(@RequestParam("courseCode") String courseCode, @RequestParam("name") String name, @RequestParam("isOffered") boolean isOffered, @RequestParam("professorid") int professorid) {
		return adserve.addCourse(courseCode, name, isOffered, professorid);
	}
	
	/**
	 * it delete a course
	 * @param courseCode
	 * @return boolean
	 */
	@RequestMapping(value = "/admin/remove/course/{id}", method = RequestMethod.DELETE)
	public boolean deleteCourse(@PathVariable("id") String courseCode) {
		return adserve.removeCourse(courseCode);
	}
	
	/**
	 * it approves all students
	 * @return boolean
	 */
	@RequestMapping(value = "/admin/approve/all", method = RequestMethod.POST, consumes = "application/json")
	public boolean approveAll() {
		adserve.approveAll();
		return true;
	}
	
	/**
	 * it approves an unapproved student
	 * @param studentid
	 * @return boolean
	 */
	@RequestMapping(value = "/admin/approve/{id}", method = RequestMethod.POST, consumes = "application/json")
	public boolean aprroveStudent(@PathVariable("id") int studentid) {
		adserve.approveStudentRegistration(studentid);
		return true;
	}
	
}
