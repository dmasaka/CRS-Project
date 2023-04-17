/**
 * 
 */
package com.incedo.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incedo.controller.CourseRegistrationController;
import com.incedo.entity.CourseRegistration;
import com.incedo.repository.CourseRegistrationRepository;

/**
 * @author David Masaka
 *
 */
@ExtendWith(MockitoExtension.class)
class CourseRegTestRest {

	@InjectMocks
	CourseRegistrationController coreg;

	@Mock
	CourseRegistrationRepository coregrep;

	/**
	 * this is a test for adding/updating fetching and deleting 
	 */
	@Test
	public void CourseRegistrationAddUpdateTest() {
		// save a new CourseRegistration
		List<CourseRegistration> init = new ArrayList<>();
		CourseRegistration coreg1 = new CourseRegistration("FUN111", 112, "None");
		init.add(coreg1);
		coreg.add(coreg1);
		when(coregrep.findCourseRegistrationByStudent(Mockito.anyInt())).thenReturn(init);
		assertTrue(coreg.getRegistrationsByStudent(112).size() > 0, "CourseRegistration was added");
		List<CourseRegistration> unap = coreg.getRegistrationsByStudent(112);
		CourseRegistration coreg2 = unap.stream().filter(st -> st.getCoursecode().equals(coreg1.getCoursecode()))
				.findFirst().get();
		assertNotNull(coreg2, "added to list");
		coreg.delete(coreg2.getCourseregid());
		init.remove(0);
		when(coregrep.findCourseRegistrationByStudent(Mockito.anyInt())).thenReturn(init);
		assertTrue(coreg.getRegistrationsByStudent(112).size() == 0, "deleted CourseRegistration");
	}

}