/**
 * 
 */
package com.incedo.app;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.incedo.entity.Course;
import com.incedo.repository.CourseRepository;

/**
 * @author David Masaka
 *
 */
@SpringBootTest
public class CourseTestJPA {

	@Autowired
	private CourseRepository cour;


	@Test
	void testCourse() {
		int start = cour.findAll().size();
		cour.save(new Course("FUN111", "Fun is Fun", false, 23));
		assertTrue("Course was added", cour.findAll().size() > start);
		Optional<Course> cr = cour.findById("FUN111");
		assertTrue("right Course is fetched", cr.isPresent());
		cr.ifPresent(cu -> {
			cu.setName("Fun Can Fun");
			cour.save(cu);
		});
		cr = cour.findById("FUN111");
		cr.ifPresent(cm -> {
			assertTrue("name is changed", cm.getName().equals("Fun Can Fun"));
			List<Course> sl = new ArrayList<>();
			sl.add(cm);
			cour.deleteAllInBatch(sl);
		});
		assertTrue("Course deleted", cour.findAll().size() == start);
	}

}

