/**
 * 
 */
package com.incedo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.Course;
import com.incedo.entity.CourseRegistration;
import com.incedo.entity.Notification;
import com.incedo.entity.Payment;
import com.incedo.repository.CourseRegistrationRepository;
import com.incedo.repository.CourseRepository;
import com.incedo.repository.NotificationRepository;
import com.incedo.repository.PaymentRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cr")
public class CourseRegistrationController {
	@Autowired
	CourseRegistrationRepository crrepo;

	@Autowired
	CourseRepository crepo;

	@Autowired
	PaymentRepository payrepo;

	@Autowired
	NotificationRepository norepo;

	/**
	 * adds a course registration (used by student)
	 * 
	 * @param cr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> add(@RequestBody CourseRegistration cr) {
		cr.setGrade("None");
		Map<String, String> map = new TreeMap<>();
		if (crrepo.findCourseRegistrationByStudentAndCode(cr.getStudentid(), cr.getCoursecode()) == null) {
			crrepo.save(cr);
			map.put("status", "200");
		} else {
			map.put("error", "Registration already exist");
		}
		return map;
	}

	/**
	 * changes a course registration (used by professor)
	 * 
	 * @param cr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = "application/json")
	public CourseRegistration change(@RequestBody CourseRegistration cr) {
		int numOfCour = crrepo.findCourseRegistrationByStudent(cr.getStudentid()).size();
		if (numOfCour > 6) {
			Payment pay = payrepo.findPaymentByStudent(cr.getStudentid());
			if (pay != null) {
				pay.setAmount(pay.getAmount() + 200);
			} else {
				int classNum = crrepo.findCourseRegistrationByStudent(cr.getStudentid()).size() + 1;
				payrepo.save(new Payment(cr.getStudentid(), null, classNum * 200, false));
			}
			crrepo.save(cr);
		} else {
			norepo.save(new Notification(cr.getStudentid(), 27));
		}
		return cr;
	}

	/**
	 * deletes a course registration
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable("id") Integer id) {
		Map<String, String> rmap = new TreeMap<>();
		Optional<CourseRegistration> coreg = crrepo.findById(id);
		coreg.ifPresentOrElse(cor -> {
			Payment pay = payrepo.findPaymentByStudent(cor.getStudentid());
			pay.setAmount(pay.getAmount() - 200);
			payrepo.save(pay);
			List<Integer> did = new ArrayList<>();
			did.add(id);
			crrepo.deleteAllByIdInBatch(did);
			rmap.put("status", "200");
		}, () -> {
			rmap.put("status", "500");
		});
		return rmap;
	}

	/**
	 * shows all the course registrations of a student
	 * 
	 * @param studentid
	 * @return
	 */
	@RequestMapping(value = "/student/{studentid}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationsByStudent(@PathVariable("studentid") Integer studentid) {
		System.out.println("/student getting all course registrations from " + studentid);
		return crrepo.findCourseRegistrationByStudent(studentid);
	}

	/**
	 * gets course registrations by professor
	 * 
	 * @param profid
	 * @return
	 */
	@RequestMapping(value = "/professor/{profid}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationsByProf(@PathVariable("profid") Integer profid) {
		System.out.println("/professor getting all the course registrations from professor " + profid);
		List<Course> clist = new ArrayList<>();
		List<CourseRegistration> crlist = new ArrayList<>();
		clist = crepo.findCoursesByProfessor(profid);
		for (Course cor : clist) {
			crlist.addAll(crrepo.findCourseRegistrationByCode(cor.getCourseCode()));
		}
		return crlist;
	}

	/**
	 * shows all the students registered in a course
	 * 
	 * @param courseCode
	 * @return
	 */
	@RequestMapping(value = "/course/{coursecode}", method = RequestMethod.GET, produces = "application/json")
	public List<CourseRegistration> getRegistrationByCourse(@PathVariable("coursecode") String courseCode) {
		System.out.println("/course getting all course registration from course " + courseCode);
		return crrepo.findCourseRegistrationByCode(courseCode);
	}

}
