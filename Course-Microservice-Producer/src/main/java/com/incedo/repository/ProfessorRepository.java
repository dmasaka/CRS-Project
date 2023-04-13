/**
 * 
 */
package com.incedo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.incedo.entity.Professor;

/**
 * @author David Masaka
 *
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
	/**
	 * find user by username for login
	 * @param username
	 * @return professor
	 */
	@Query("Select s from Professor s where LOWER(s.username) = LOWER(?1)")
	Professor findProfessorByUsername(String username);
}
