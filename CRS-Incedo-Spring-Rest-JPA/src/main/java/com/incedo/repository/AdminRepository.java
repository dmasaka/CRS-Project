package com.incedo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.incedo.entity.Admin;

/**
 * @author David Masaka
 *
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	/**
	 * find user by username for login
	 * @param username
	 * @return admin
	 */
	@Query("Select s from Admin s where LOWER(s.username) = LOWER(?1)")
	Admin findAdminByUsername(String username);
}
