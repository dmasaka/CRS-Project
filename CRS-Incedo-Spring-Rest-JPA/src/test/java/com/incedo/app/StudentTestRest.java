/**
 * 
 */
package com.incedo.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incedo.controller.StudentController;
import com.incedo.entity.Student;
import com.incedo.repository.StudentRepository;

/**
 * @author David Masaka
 *
 */
@ExtendWith(MockitoExtension.class)
class StudentTestRest {
	
	@InjectMocks
	StudentController stud;
	
	@Mock
	StudentRepository studrep;

	/**
	 * this is a test for adding/updating fetching and deleting 
	 */
	@Test
	public void studentAddUpdateTest() {
		//save a new student
		List<Student> init = new ArrayList<>();
		Student stud1 = new Student("Per Son", "PerS", "Per12", "Place", 0, "Any", false);
		init.add(stud1);
		stud.addStudent(stud1);
		when(studrep.findAll()).thenReturn(init);
		System.out.println(stud.getAllStudents());
		assertTrue( stud.getAllStudents().size() > 0, "student was added");
		when(studrep.listAllUnapproved()).thenReturn(init);
		List<Student> unap = stud.listUnapproved();
		Student stud2 = unap.stream().filter(st -> st.getUsername().equals(stud1.getUsername())).findFirst().get();
		assertNotNull(stud2, "added to unapproved list");
		stud.approveOne(stud2.getUserId());
		Map<String, String> cred = new TreeMap<>();
		cred.put("username", "PerS");
		cred.put("password", "Per12");
//		System.out.println(studrep.findStudentByUsername("PerS"));
		when(studrep.findStudentByUsername(Mockito.anyString())).thenReturn(stud1);
		Student stud3 = stud.login(cred);
		assertNotNull(stud3, "Approved and Logged in");
		stud.deleteStudent(stud3.getUserId());
		init.remove(0);
		when(studrep.findAll()).thenReturn(init);
		assertTrue(stud.getAllStudents().size() == 0, "deleted student");
	}

}
