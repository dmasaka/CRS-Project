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

import com.incedo.entity.Professor;
import com.incedo.repository.ProfessorRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/professors")
public class ProfessorController {
	@Autowired
	ProfessorRepository profrepo;

	/**
	 * list all Professors
	 * @return list of Professors
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Professor> getAllProfessors(){
		System.out.println(profrepo.findAll());
		return profrepo.findAll();
	}
	
	/**
	 * logs in user
	 * @param username
	 * @param password
	 * @return Professor object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Professor login(@RequestBody Map<String, String> payload) {
		Professor prof = profrepo.findProfessorByUsername(payload.get("username"));
		if(prof != null && prof.getPassword().equals(payload.get("password"))) {
			return prof;
		}
		return null;
	}
	
	/**
	 * add Professor
	 * @param prof
	 * @return Professor object
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Professor addProfessor(@RequestBody Professor prof) {
		System.out.println(prof);
		try {
			return profrepo.save(prof);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	
	/**
	 * delete Professor
	 * @param prof
	 * @return boolean
	 */
	@RequestMapping(value = "/delete/{profid}", method = RequestMethod.DELETE)
	public boolean deleteProfessor(@PathVariable("profid") Integer prof) {
		System.out.println(prof);
		List<Integer> nums = new ArrayList<>();
		nums.add(prof);
		try {
			profrepo.deleteAllByIdInBatch(nums);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
}
