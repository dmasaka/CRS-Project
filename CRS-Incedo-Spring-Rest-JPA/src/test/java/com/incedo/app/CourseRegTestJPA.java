/**
 * 
 */
package com.incedo.app;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.incedo.entity.CourseRegistration;
import com.incedo.repository.CourseRegistrationRepository;

/**
 * @author David Masaka
 *
 */
@SpringBootTest
class CourseRegTestJPA {

	@Autowired
	private CourseRegistrationRepository coreg;

	@Test
	void testCourseRegistration() {
		int start = coreg.findAll().size();
		coreg.save(new CourseRegistration("ART101", 123, "None"));
		assertTrue("coregseRegistration was added", coreg.findAll().size() > start);
		List<CourseRegistration> listcr = coreg.findCourseRegistrationByCode("ART101");
		CourseRegistration cr = listcr.stream().filter(cm -> cm.getStudentid() == 123).findFirst().get();
		assertTrue("right CourseRegistration is fetched", cr != null);
		cr.setGrade("A");
		coreg.save(cr);
		listcr = coreg.findCourseRegistrationByCode("ART101");
		cr = listcr.stream().filter(cm -> cm.getStudentid() == 123).findFirst().get();
		assertTrue("grade is changed", cr.getGrade().equals("A"));
		List<CourseRegistration> sl = new ArrayList<>();
		sl.add(cr);
		coreg.deleteAllInBatch(sl);
		assertTrue("CourseRegistration deleted", coreg.findAll().size() == start);
	}

}
