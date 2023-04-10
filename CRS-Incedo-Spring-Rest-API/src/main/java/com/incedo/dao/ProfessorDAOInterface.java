/**
 * 
 */
package com.incedo.dao;

import java.util.List;

import com.incedo.bean.Professor;
import com.incedo.exception.UserNotFoundException;

/**
 * @author David Masaka
 *
 */
public interface ProfessorDAOInterface {
	/**
	 * list all professors
	 * @return
	 */
	public List<Professor> listOfProfessors();
	/**
	 * login for professsor
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	public Professor login(String username, String password) throws UserNotFoundException;
	/**
	 * creates a new professor
	 * @param prof
	 */
	public void add(Professor prof);
}
