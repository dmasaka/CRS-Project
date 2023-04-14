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
	
}
