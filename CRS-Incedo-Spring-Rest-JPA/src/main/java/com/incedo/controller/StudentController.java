/**
 * 
 */
package com.incedo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.Student;
import com.incedo.repository.StudentRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentRepository studrepo;

	/**
	 * list all students
	 * @return list of students
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Student> getAllStudents(){
		return studrepo.findAll();
	}
	
	
	
	/**
	 * logs in user
	 * @param username
	 * @param password
	 * @return student object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
//	public Student login(@RequestParam("username") String username, @RequestParam("password") String password) {
		public Student login(@RequestBody Map<String, String> payload) {
		Student stud = studrepo.findStudentByUsername(payload.get("username"));
		if(stud != null && stud.getPassword().equals(payload.get("password"))) {
			return stud;
		}
		return null;
	}
	
	/**
	 * add student
	 * @param stud
	 * @return student object
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Student addStudent(@RequestBody Student stud) {
		stud.setApproved(false);
		try {
			return studrepo.save(stud);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	
	/**
	 * delete student
	 * @param stud
	 * @return boolean
	 */
	@RequestMapping(value = "/delete/{studentid}", method = RequestMethod.DELETE)
	public boolean deleteStudent(@PathVariable("studentid") Integer stud) {
		System.out.println(stud);
		List<Integer> nums = new ArrayList<>();
		nums.add(stud);
		try {
			studrepo.deleteAllByIdInBatch(nums);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	@RequestMapping(value = "/unapproved", method = RequestMethod.GET, produces = "application/json")
	public List<Student> listUnapproved(){
		System.out.println(studrepo.listAllUnapproved());
		return studrepo.listAllUnapproved();
	}
	
	@RequestMapping(value = "/approveall", method = RequestMethod.POST)
	public void approveAll() {
		studrepo.approveAll();
	}
	
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.POST)
	public void approveAll(@PathVariable("id") int studid) {
		studrepo.approveStudent(studid);
	}
}
