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
	/**
	 * returns a list of all the admins
	 * @return list of admins
	 */
	public List<Admin> listOfAdmins();
	/**
	 * logins the admin
	 * @param username
	 * @param password
	 * @return an admin object
	 * @throws UserNotFoundException
	 */
	public Admin login(String username, String password) throws UserNotFoundException;
	/**
	 * approves a student
	 * @param studentid
	 */
	public void approveStudent(int studentid);
}
