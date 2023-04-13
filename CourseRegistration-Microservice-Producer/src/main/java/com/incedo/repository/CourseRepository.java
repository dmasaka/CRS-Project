/**
 * 
 */
package com.incedo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incedo.entity.Course;

/**
 * @author David Masaka
 *
 */
public interface CourseRepository extends JpaRepository<Course, String> {
	@Query("select c from Course c where c.professorId = :profid")
	List<Course> findCoursesByProfessor(@Param("profid") int professorid);
}
