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

import com.incedo.entity.Professor;
import com.incedo.repository.ProfessorRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
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
		System.out.println("/all getting all professors");
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
		System.out.println("/login logging in professor " + payload.get("username"));
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
	public Map<String, String> addProfessor(@RequestBody Professor prof) {
		System.out.println("/add adding professor " + prof.toString());
		Map<String, String> resp = new TreeMap<>();
		if (profrepo.findProfessorByUsername(prof.getUsername()) == null) {
			try {
				profrepo.save(prof);
				resp.put("status", "200");
			} catch (Exception ex) {
				System.out.println(ex);
				resp.put("status", "500");
			}
		} else {
			resp.put("status", "403");
			resp.put("err", "username already exists");
		}
		return resp;
	}
	
	/**
	 * add Professor
	 * @param prof
	 * @return Professor object
	 */
	@RequestMapping(value = "/change", method = RequestMethod.PUT, produces = "application/json")
	public Map<String, String> changeProfessor(@RequestBody Professor prof) {
		System.out.println("/change change to professor " + prof.toString());
		Map<String, String> resp = new TreeMap<>();
		if (profrepo.findProfessorByUsername(prof.getUsername()) != null) {
			try {
				profrepo.save(prof);
				resp.put("status", "200");
			} catch (Exception ex) {
				System.out.println(ex);
				resp.put("status", "500");
			}
		} else {
			resp.put("status", "403");
			resp.put("err", "user does not exist");
		}
		return resp;
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Map<String, String> changePassword(@RequestBody Map<String, String> payload){
		System.out.println("/password changing password of professor " + payload.get("username"));
		Map<String, String> rmap = new TreeMap<>();
		String username = payload.get("username");
		String password = payload.get("password");
		String npassword = payload.get("newpassword");
		Professor prof = profrepo.findProfessorByUsername(username);
		if (prof.getPassword().equals(password)) {
			prof.setPassword(npassword);
			profrepo.save(prof);
			rmap.put("status", "200");
			return rmap;
		}
		rmap.put("status", "408");
		return rmap;
	}
	
	/**
	 * delete Professor
	 * @param prof
	 * @return boolean
	 */
	@RequestMapping(value = "/delete/{profid}", method = RequestMethod.DELETE)
	public boolean deleteProfessor(@PathVariable("profid") Integer prof) {
		System.out.println("/delete deleting professor with id "+ prof.toString());
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
