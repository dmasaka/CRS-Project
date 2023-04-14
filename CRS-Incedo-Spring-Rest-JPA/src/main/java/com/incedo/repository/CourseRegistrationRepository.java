/**
 * 
 */
package com.incedo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incedo.entity.CourseRegistration;

/**
 * @author David Masaka
 *
 */
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Integer> {
	@Query("Select c from CourseRegistration c where c.studentid = LOWER(?1)")
	List<CourseRegistration> findCourseRegistrationByStudent(int studentid);
	@Query("Select c from CourseRegistration c where LOWER(c.coursecode) = LOWER(?1)")
	List<CourseRegistration> findCourseRegistrationByCode(String courseCode);
}
