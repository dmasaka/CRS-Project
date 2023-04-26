/**
 * 
 */
package com.incedo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.incedo.entity.Student;

/**
 * @author David Masaka
 *
 */

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {
	/**
	 * find user by username for login
	 * @param username
	 * @return student
	 */
	@Query("Select s from Student s where LOWER(s.username) = LOWER(?1) and s.isApproved = true")
	Student findStudentByUsername(String username);
	
	@Query("Select s from Student s where LOWER(s.username) = LOWER(?1)")
	Student checkStudentByUsername(String username);
	
	@Query("select s from Student s where s.isApproved=false")
	List<Student> listAllUnapproved();
	
	@Modifying
	@Query("update Student s set s.isApproved = true where 1 = 1")
	void approveAll();
	
	@Modifying
	@Query("update Student s set s.isApproved = true where s.userId = :userid")
	void approveStudent(@Param("userid") int studentid);
}
