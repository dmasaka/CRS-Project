/**
 * 
 */
package com.incedo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.CourseRegistration;
import com.incedo.repository.CourseRegistrationRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/cr")
public class CourseRegistrationController {
	@Autowired
	CourseRegistrationRepository crrepo;
	
	/**
	 * adds a course registration
	 * @param cr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public CourseRegistration add(@RequestBody CourseRegistration cr) {
//		cr.setGrade("None");
		crrepo.save(cr);
		return cr;
	}
	
	/**
	 * deletes a course registration
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable("id") int id) {
		List<Integer> did = new ArrayList<>();
		did.add(id);
		crrepo.deleteAllByIdInBatch(did);
		return true;
	}
	
	/**
	 * shows all the course registrations of a student
	 * @param studentid
	 * @return
	 */
	@RequestMapping(value = "/student/{studentid}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationsByStudent(@PathVariable("studentid") Integer studentid){
		System.out.println("studentid: "+ studentid);
		return crrepo.findCourseRegistrationByStudent(studentid);
	}
	
	/**
	 * shows all the students registered in a course
	 * @param courseCode
	 * @return
	 */
	@RequestMapping(value = "/course/{coursecode}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationByCourse(@PathVariable("coursecode") String courseCode){
		return crrepo.findCourseRegistrationByCode(courseCode);
	}
	
}
