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

import com.incedo.entity.Student;
import com.incedo.repository.StudentRepository;

/**
 * @author David Masaka
 *
 */
@SpringBootTest
public class StudentTestJPA {
	@Autowired
	private StudentRepository stud;


	@Test
	void testStudent() {
		int start = stud.findAll().size();
		stud.save(new Student("Per Son", "PerS", "Per12", "Place", 23, "Any", true));
		assertTrue("student was added", stud.findAll().size() > start);
		Student st = stud.findStudentByUsername("PerS");
		assertTrue("right student is fetched", st.getName().equals("Per Son"));
		st.setName("Per Sons");
		stud.save(st);
		st = stud.findStudentByUsername("PerS");
		assertTrue("name is changed", st.getName().equals("Per Sons"));
		List<Student> sl = new ArrayList<>();
		sl.add(st);
		stud.deleteAllInBatch(sl);
		assertTrue("student deleted", stud.findAll().size() == start);
	}

}
