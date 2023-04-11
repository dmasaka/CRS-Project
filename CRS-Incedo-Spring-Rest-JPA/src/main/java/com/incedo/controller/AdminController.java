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

import com.incedo.entity.Admin;
import com.incedo.repository.AdminRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	AdminRepository adrepo;

	/**
	 * list all Admins
	 * @return list of Admins
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Admin> getAllAdmins(){
		System.out.println(adrepo.findAll());
		return adrepo.findAll();
	}
	
	/**
	 * logs in user
	 * @param username
	 * @param password
	 * @return Admin object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Admin login(@RequestBody Map<String, String> payload) {
		Admin ad = adrepo.findAdminByUsername(payload.get("username"));
		if(ad != null && ad.getPassword().equals(payload.get("password"))) {
			return ad;
		}
		return null;
	}
	
	/**
	 * add Admin
	 * @param ad
	 * @return Admin object
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
	public Admin addAdmin(@RequestBody Admin ad) {
		System.out.println(ad);
		try {
			return adrepo.save(ad);
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
	
	/**
	 * delete Admin
	 * @param ad
	 * @return boolean
	 */
	@RequestMapping(value = "/delete/{adid}", method = RequestMethod.DELETE)
	public boolean deleteAdmin(@PathVariable("adid") Integer ad) {
		System.out.println(ad);
		List<Integer> nums = new ArrayList<>();
		nums.add(ad);
		try {
			adrepo.deleteAllByIdInBatch(nums);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
}
