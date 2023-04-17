/**
 * 
 */
package com.incedo.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incedo.controller.CourseController;
import com.incedo.entity.Course;
import com.incedo.repository.CourseRepository;

/**
 * @author David Masaka
 *
 */
@ExtendWith(MockitoExtension.class)
class CourseTestRest {
	
	@InjectMocks
	CourseController cour;
	
	@Mock
	CourseRepository courrep;

	/**
	 * this is a test for adding/updating fetching and deleting 
	 */
	@Test
	public void CourseAddUpdateTest() {
		//save a new Course
		List<Course> init = new ArrayList<>();
		Course cour1 = new Course("FUN111", "Insane in the Membrane", false, 103);
		init.add(cour1);
		cour.add(cour1);
		when(courrep.findAll()).thenReturn(init);
		assertTrue( cour.getAll().size() > 0, "Course was added");
		when(courrep.findAll()).thenReturn(init);
		List<Course> unap = cour.getAll();
		Course cour2 = unap.stream().filter(st -> st.getName().equals(cour1.getName())).findFirst().get();
		assertNotNull(cour2, "added to list");
		Map<String, String> cred = new TreeMap<>();
		cred.put("username", "PerS");
		cred.put("password", "Per12");
		when(courrep.findCoursesByProfessor(Mockito.anyInt())).thenReturn(init);
		List<Course> cour3 = cour.byProf(0);
		assertTrue(cour3.size() > 0, "found course by professor");
		cour.delete("FUN111");
		init.remove(0);
		when(courrep.findAll()).thenReturn(init);
		assertTrue(cour.getAll().size() == 0, "deleted Course");
	}

}