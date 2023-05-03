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

import com.incedo.entity.Admin;
import com.incedo.repository.AdminRepository;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	AdminRepository adrepo;

	/**
	 * list all Admins
	 * 
	 * @return list of Admins
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Admin> getAllAdmins() {
		System.out.println("/all getting all admins");
		return adrepo.findAll();
	}

	/**
	 * logs in user
	 * 
	 * @param username
	 * @param password
	 * @return Admin object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Admin login(@RequestBody Map<String, String> payload) {
		System.out.println("/login loggin in admin " + payload.get("username"));
		Admin ad = adrepo.findAdminByUsername(payload.get("username"));
		if (ad != null && ad.getPassword().equals(payload.get("password"))) {
			return ad;
		}
		return null;
	}

	/**
	 * add Admin
	 * 
	 * @param ad
	 * @return Admin object
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Map<String, String> addAdmin(@RequestBody Admin ad) {
		System.out.println("/add adding admin " + ad.toString());
		Map<String, String> rmap = new TreeMap<>();
		if (adrepo.findAdminByUsername(ad.getUsername()) == null) {
			try {
				adrepo.save(ad);
				rmap.put("status", "200");
			} catch (Exception ex) {
				System.out.println(ex);
				rmap.put("status", "500");
			}
		} else {
			rmap.put("status", "403");
			rmap.put("err", "username already exists");
		}
		return rmap;
	}
	
	/**
	 * add Admin
	 * 
	 * @param ad
	 * @return Admin object
	 */
	@RequestMapping(value = "/change", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public Map<String, String> changeAdmin(@RequestBody Admin ad) {
		System.out.println("/change to admin " + ad.toString());
		Map<String, String> rmap = new TreeMap<>();
		if (adrepo.findAdminByUsername(ad.getUsername()) != null) {
			try {
				adrepo.save(ad);
				rmap.put("status", "200");
			} catch (Exception ex) {
				System.out.println(ex);
				rmap.put("status", "500");
			}
		} else {
			rmap.put("status", "403");
			rmap.put("err", "user does not exist");
		}
		return rmap;
	}
	
	/**
	 * changes password of user
	 * @param payload
	 * @return
	 */
	@RequestMapping(value = "/password", method = RequestMethod.POST, produces = "application/json")
	public Map<String, String> changePassword(@RequestBody Map<String, String> payload){
		System.out.println("/password changing password of admin " + payload.get("username"));
		Map<String, String> rmap = new TreeMap<>();
		String username = payload.get("username");
		String password = payload.get("password");
		String npassword = payload.get("newpassword");
		Admin ad = adrepo.findAdminByUsername(payload.get("username"));
		if (ad.getPassword().equals(password)) {
			ad.setPassword(npassword);
			adrepo.save(ad);
			rmap.put("status", "200");
			return rmap;
		}
		rmap.put("status", "408");
		return rmap;
	}

	/**
	 * delete Admin
	 * 
	 * @param ad
	 * @return boolean
	 */
	@RequestMapping(value = "/delete/{adid}", method = RequestMethod.DELETE)
	public boolean deleteAdmin(@PathVariable("adid") Integer ad) {
		System.out.println("/delete deleting admin with id " + ad);
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
