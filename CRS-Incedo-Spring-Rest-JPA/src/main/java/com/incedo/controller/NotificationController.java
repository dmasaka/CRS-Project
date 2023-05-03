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
import org.springframework.web.bind.annotation.RestController;

import com.incedo.entity.Notification;
import com.incedo.repository.NotificationRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/not")
public class NotificationController {
	@Autowired
	NotificationRepository norepo;
	
	@RequestMapping(value = "/{studid}", method = RequestMethod.GET, produces = "application/json")
	List<Notification> getAllById(@PathVariable("studid") int studentid){
		System.out.println("/not/studid getting notifications of student " + studentid);
		return norepo.findNotificationByStudentid(studentid);
	}
	
	@RequestMapping(value = "/{notid}", method = RequestMethod.DELETE)
	Map<String, String> deleteId(@PathVariable("notid") int notid){
		System.out.println("/not/notid removing notification " + notid);
		Map<String, String> rmap = new TreeMap<>();
		List<Integer> nots = new ArrayList<>();
		nots.add(notid);
		norepo.deleteAllByIdInBatch(nots);
		rmap.put("status", "200");
		return rmap;
	}
}
