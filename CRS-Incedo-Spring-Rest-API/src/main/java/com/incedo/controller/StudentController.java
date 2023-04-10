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
import com.incedo.bean.Student;
import com.incedo.service.PaymentServiceOperation;
import com.incedo.service.StudentServiceOperation;

/**
 * @author David Masaka
 *
 */
@RestController
public class StudentController {
	@Autowired
	private StudentServiceOperation studserve;
	
	@Autowired
	private PaymentServiceOperation payserve;
	
	/**
	 * it finds student
	 * @param username
	 * @param password
	 * @return student
	 */
	@RequestMapping(value = "/student/login", method = RequestMethod.POST, consumes = "application/json")
	public Student login(@RequestParam("username") String username, @RequestParam("password") String password){
		return studserve.findStudentId(username, password);
	}
	
	/**
	 * adds new student
	 * @param studentid
	 * @param courseCode
	 * @return boolean
	 */
	@RequestMapping(value = "/student/add", method = RequestMethod.POST, consumes = "application/json")
	public boolean add(@RequestParam("studentid") int studentid, @RequestParam("courseCode") String courseCode) {
		studserve.addCourse(studentid, courseCode);
		return true;
	}
	
	/**
	 * deletes student registration
	 * @param studentid
	 * @param courseCode
	 * @return boolean
	 */
	@RequestMapping(value = "/student/delete", method = RequestMethod.POST, consumes = "application/json")
	public boolean delete(@RequestParam("studentid") int studentid, @RequestParam("courseCode") String courseCode) {
		studserve.deleteCourse(studentid, courseCode);
		return true;
	}
	
	/**
	 * allows student to pay offline
	 * @param studentid
	 * @param bankName
	 * @param checkNumber
	 * @return boolean
	 */
	@RequestMapping(value = "/payment/offline", method = RequestMethod.POST, consumes = "application/json")
	public boolean payoff(@RequestParam("studentid") int studentid, @RequestParam("bankName") String bankName, @RequestParam("checkNumber") int checkNumber) {
		payserve.offline(studentid, bankName, checkNumber);
		return true;
	}
	
	/**
	 * allows student to pay online
	 * @param studentid
	 * @param cardNumber
	 * @param cardType
	 * @return boolean
	 */
	@RequestMapping(value = "/payment/online", method = RequestMethod.POST, consumes = "application/json")
	public boolean payon(@RequestParam("studentid") int studentid, @RequestParam("cardNumber") int cardNumber, @RequestParam("cardType") String cardType) {
		payserve.onlineCard(studentid, cardNumber, cardType);
		return true;
	}
	
	/**
	 * returns course registrations of student
	 * @param studentid
	 * @return list of course registrations
	 */
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> viewGrades(@PathVariable("id") int studentid) {
		return studserve.viewGrades(studentid);
	}
}
