/**
 * 
 */
package com.incedo.dao;

import java.util.List;

import com.incedo.bean.Admin;
import com.incedo.exception.UserNotFoundException;

/**
 * @author David Masaka
 *
 */
public interface AdminDAOInterface {
	public List<Admin> listOfAdmins();
	public Admin login(String username, String password) throws UserNotFoundException;
	public void approveStudent(int studentid);
}
