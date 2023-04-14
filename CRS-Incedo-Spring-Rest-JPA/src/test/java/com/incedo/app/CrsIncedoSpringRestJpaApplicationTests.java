package com.incedo.app;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.incedo.entity.Admin;
import com.incedo.entity.Course;
import com.incedo.entity.CourseRegistration;
import com.incedo.entity.Professor;
import com.incedo.entity.Student;
import com.incedo.repository.AdminRepository;
import com.incedo.repository.CourseRegistrationRepository;
import com.incedo.repository.CourseRepository;
import com.incedo.repository.ProfessorRepository;
import com.incedo.repository.StudentRepository;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CrsIncedoSpringRestJpaApplicationTests {
	@Autowired
	private StudentRepository stud;

	@Autowired
	private AdminRepository adm;

	@Autowired
	private ProfessorRepository prof;

	@Autowired
	private CourseRepository cour;

	@Autowired
	private CourseRegistrationRepository coreg;

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

	@Test
	void testProfessor() {
		int start = prof.findAll().size();
		prof.save(new Professor("Per Son", "PerS", "Per12", "Place", "Depart", "Desi", new java.sql.Date(84848448)));
		assertTrue("Professor was added", prof.findAll().size() > start);
		Professor pf = prof.findProfessorByUsername("PerS");
		assertTrue("right Professor is fetched", pf.getName().equals("Per Son"));
		pf.setName("Per Sons");
		prof.save(pf);
		pf = prof.findProfessorByUsername("PerS");
		assertTrue("name is changed", pf.getName().equals("Per Sons"));
		List<Professor> sl = new ArrayList<>();
		sl.add(pf);
		prof.deleteAllInBatch(sl);
		assertTrue("Professor deleted", prof.findAll().size() == start);
	}

	@Test
	void testAdmin() {
		int start = adm.findAll().size();
		adm.save(new Admin("Per Son", "PerS", "Per12", "Place", new Date(48848448)));
		assertTrue("Admin was added", adm.findAll().size() > start);
		Admin ad = adm.findAdminByUsername("PerS");
		assertTrue("right admin is fetched", ad.getName().equals("Per Son"));
		ad.setName("Per Sons");
		adm.save(ad);
		ad = adm.findAdminByUsername("PerS");
		assertTrue("name is changed", ad.getName().equals("Per Sons"));
		List<Admin> sl = new ArrayList<>();
		sl.add(ad);
		adm.deleteAllInBatch(sl);
		assertTrue("Admin deleted", adm.findAll().size() == start);
	}

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
