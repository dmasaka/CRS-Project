/**
 * 
 */
package com.incedo.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David Masaka
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class TestController {
	@RequestMapping(value="/", method = RequestMethod.GET, produces = "application/json")
	public Map<String, String> pleaseWork(){
		Map<String, String> rmap = new TreeMap<>();
		rmap.put("status", "at least you are here");
		return rmap;
	}
}
