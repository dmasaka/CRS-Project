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
	public List<Professor> listOfProfessors();
	public Professor login(String username, String password) throws UserNotFoundException;
	public void add(Professor prof);
}
