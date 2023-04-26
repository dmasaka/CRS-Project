/**
 * 
 */
package com.incedo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.Payment;
import com.incedo.repository.CourseRegistrationRepository;
import com.incedo.repository.PaymentRepository;
import com.incedo.repository.StudentRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/pay")
public class PaymentController {
	@Autowired
	PaymentRepository prepo;
	
	@Autowired
	CourseRegistrationRepository crrepo;
	
	/**
	 * handles offline payment
	 * @param payload
	 * @return payment
	 */
	@RequestMapping(value = "/offline", method = RequestMethod.POST, produces = "application/json")
	public Payment offPay(@RequestBody Map<String, Object> payload) {
		if (Integer.compare((int) payload.get("checkNumber"), 0) == 0) return null;
		if (payload.get("bankName").equals("None")) return null;
		Payment npay = new Payment((int) payload.get("studentId"), "None", 100, true);
		return prepo.save(npay);
	}
	
	/**
	 * handles online payment
	 * @param payload
	 * @return payment
	 */
	@RequestMapping(value = "/online", method = RequestMethod.POST, produces = "application/json")
	public Payment onPay(@RequestBody Map<String, Object> payload) {
		if (Integer.compare((int) payload.get("cardNumber"), 0) == 0) return null;
		if (payload.get("cardType").equals("None")) return null;
		Payment npay = new Payment((int) payload.get("studentId"), "None", 100, true);
		return prepo.save(npay);
	}
	
	/**
	 * list the payments made by a student
	 * @param studentid
	 * @return lsit of payments
	 */
	@RequestMapping(value = "/student/{studentid}", method = RequestMethod.GET, produces = "application/json")
	public List<Payment> listAll(@PathVariable("studentid") int studentid){
		return prepo.findPaymentByStudent(studentid);
	}

}
