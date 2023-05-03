/**
 * 
 */
package com.incedo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.Course;
import com.incedo.repository.CourseRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseRepository crepo;

	/**
	 * lists all courses
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Course> getAll() {
		System.out.println("/all getting all courses");
		return crepo.findAll();
	}

	/**
	 * lists all the courses
	 * @param cr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> add(@RequestBody Course cr) {
		Map<String, String> rmap = new TreeMap<>();
		try {
		System.out.println("/add adding " + cr.getCourseCode() + " course");
		crepo.save(cr);
		rmap.put("status", "200");
		} catch (Exception ex){
			rmap.put("status", "400");
		}
		return rmap;
	}

	/**
	 * deletes a course
	 * 
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/delete/{courseid}", method = RequestMethod.DELETE, produces = "application/json")
	public boolean delete(@PathVariable("courseid") String courseId) {
		List<String> ids = new ArrayList<>();
		ids.add(courseId);
		crepo.deleteAllByIdInBatch(ids);
		return true;
	}

	/**
	 * list courses by professor
	 * 
	 * @param profid
	 * @return
	 */
	@RequestMapping(value = "/professor/{profid}", method = RequestMethod.GET, produces = "application/json")
	public List<Course> byProf(@PathVariable("profid") int profid) {
		return crepo.findCoursesByProfessor(profid);
	}
}
