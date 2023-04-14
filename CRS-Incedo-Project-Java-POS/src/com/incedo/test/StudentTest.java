/**
 * 
 */
package com.incedo.test;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incedo.service.StudentInterface;
import com.incedo.service.StudentService;

/**
 * @author David Masaka
 *
 */
public class StudentTest {

	StudentInterface stud = new StudentService();
	static int studid;
	
	@Test
	public void testAddStudent() {
		stud.addStudent("judge judy", "judyj", "judy12", "Court");
		studid = stud.findStudentId("judyj", "judy12");
		System.out.println("please work " + studid);
		assertTrue(studid > 1);
	}
	
	@Test
	public void testGrades() {
		TreeMap<String, String> grades = stud.viewGrades(studid);
		assertTrue(grades.size() == 0);
	}
	
	@Test
	public void testCourse() {
		stud.addCourse(studid, "ART103");
		TreeMap<String, String> grades = stud.viewGrades(studid);
		assertNotEquals(grades.size(), 0);
		stud.deleteCourse(studid, "ART103");
		grades = stud.viewGrades(studid);
		assertEquals(grades.size(), 0);
	}

}
