/**
 * 
 */
package com.incedo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incedo.dao.CourseDAOImpl;
import com.incedo.service.AdminService;
import com.incedo.service.StudentService;

/**
 * @author David Masaka
 *
 */
public class AdminTest {
	AdminService adm = new AdminService();
	StudentService stud = new StudentService();
	CourseDAOImpl cor = new CourseDAOImpl();
	static int admid;

	
	@Test
	public void testLogin() {
		admid = adm.findAdminId("VickyS", "Vicky12");
		assertTrue(admid > 1);
	}
	
	@Test
	public void testApproval() {
		stud.addStudent("Norm Al", "NormA", "Norm12", "Anywhere");
		assertNotEquals(adm.listOfUnapproved().size(), 0);
		adm.approveAll();
		assertEquals(adm.listOfUnapproved().size(), 0);
		stud.addStudent("Ab Normal", "AbN", "Ab12", "Somewhere");
		assertEquals(adm.listOfUnapproved().size(), 1);
	}
	
	@Test
	public void testCourse() {
		int start = cor.listOfCourses().size();
		adm.addCourse("FUN111", "Fun is One", true, 103);
		int end = cor.listOfCourses().size();
		assertTrue("course was added", end > start);
		adm.removeCourse("FUN111");
		end = cor.listOfCourses().size();
		assertTrue("course was removed", end == start);
	}

}
