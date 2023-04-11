/**
 * 
 */
package com.incedo.controller;

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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public CourseRegistration add(@RequestBody CourseRegistration cr) {
		cr.setGrade("None");
		crrepo.save(cr);
		return cr;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public boolean delete(@PathVariable("id") int id) {
		List<Integer> did = Arrays.asList(id);
		crrepo.deleteAllByIdInBatch(did);
		return true;
	}
	
	@RequestMapping(value = "/student/{studentid}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationsByStudent(@PathVariable("studentid") Integer studentid){
		System.out.println("studentid: "+ studentid);
		return crrepo.findCourseRegistrationByStudent(studentid);
	}
	
	@RequestMapping(value = "/course/{coursecode}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationByCourse(@PathVariable("coursecode") String courseCode){
		return crrepo.findCourseRegistrationByCode(courseCode);
	}
	
}
