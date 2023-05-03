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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.Payment;
import com.incedo.entity.Student;
import com.incedo.repository.PaymentRepository;
import com.incedo.repository.StudentRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentRepository studrepo;
	
	@Autowired
	PaymentRepository payrepo;

	/**
	 * list all students
	 * @return list of students
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Student> getAllStudents(){
		System.out.println("/all getting all students");
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
		System.out.println("/login logging in student " + payload.get("username"));
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
	public Map<String, String> addStudent(@RequestBody Student stud) {
		System.out.println("/add adding student" + stud.toString());
		stud.setApproved(false);
		Map<String, String> map = new TreeMap<>();
		if (studrepo.checkStudentByUsername(stud.getUsername()) != null) {
			map.put("status", "403");
			map.put("error", "user already exist");
			return map;
		}
		try {
			payrepo.save(new Payment(stud.getUserId(), "student", 0, false));
			studrepo.save(stud);
			map.put("status", "200");
		} catch (Exception ex) {
			map.put("status", "500");
			System.out.println(ex);
		}
		return map;
	}
	
	/**
	 * add student
	 * @param stud
	 * @return student object
	 */
	@RequestMapping(value = "/change", method = RequestMethod.PUT, produces = "application/json")
	public Map<String, String> changeStudent(@RequestBody Student stud) {
		System.out.println("/change change to student " + stud.toString());
		Map<String, String> map = new TreeMap<>();
		if (studrepo.checkStudentByUsername(stud.getUsername()) == null) {
			map.put("status", "403");
			map.put("error", "user does not exist");
			return map;
		}
		try {
			studrepo.save(stud);
			map.put("status", "200");
		} catch (Exception ex) {
			map.put("status", "500");
			System.out.println(ex);
		}
		return map;
	}
	
	/**
	 * changes password of user
	 * @param payload
	 * @return
	 */
	@RequestMapping(value = "/password", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> changePassword(@RequestBody Map<String, String> payload){
		System.out.println("/password changing password of student " + payload.get("username"));
		Map<String, String> rmap = new TreeMap<>();
		String username = payload.get("username");
		String password = payload.get("password");
		String npassword = payload.get("newpassword");
		Student stud = studrepo.findStudentByUsername(payload.get("username"));
		if (stud.getPassword().equals(password)) {
			stud.setPassword(npassword);
			studrepo.save(stud);
			rmap.put("status", "200");
			return rmap;
		}
		rmap.put("status", "408");
		return rmap;
	}
	
	@RequestMapping(value = "/semester", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Map<String, String> changeSemester(@RequestBody Map<String, String> payload){
		System.out.println("/semester changes semester of student " + payload.get("studentid"));
		Map<String, String> rmap = new TreeMap<>();
		studrepo.findById(Integer.parseInt(payload.get("studentid"))).ifPresentOrElse(stud -> {
			stud.setSemesterId(Integer.parseInt(payload.get("semester")));
			studrepo.save(stud);
			rmap.put("status", "200");
		}, () -> {
			rmap.put("status", "409");
			rmap.put("err", "could not save");
		});
		
		return rmap;
	}
	
	/**
	 * delete student
	 * @param stud
	 * @return boolean
	 */
	@RequestMapping(value = "/delete/{studentid}", method = RequestMethod.DELETE)
	public boolean deleteStudent(@PathVariable("studentid") Integer stud) {
		System.out.println("/delete deleting student with id " + stud.toString());
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
	
	/**
	 * @return list of unapproved students
	 */
	@RequestMapping(value = "/unapproved", method = RequestMethod.GET, produces = "application/json")
	public List<Student> listUnapproved(){
		System.out.println("/unapproved getting list of unapproved");
		return studrepo.listAllUnapproved();
	}
	
	/**
	 * approves all the students
	 */
	@RequestMapping(value = "/approveall", method = RequestMethod.POST)
	public void approveAll() {
		System.out.println("/approveall approving all students");
		studrepo.approveAll();
	}
	
	/**
	 * approves a single student
	 * @param studid
	 */
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.POST)
	public void approveOne(@PathVariable("id") Integer studid) {
		System.out.println("/approve approving student with id" + studid.toString());
		studrepo.approveStudent(studid);
	}
}
