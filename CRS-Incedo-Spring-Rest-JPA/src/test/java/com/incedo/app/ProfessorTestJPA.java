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

import com.incedo.entity.Professor;
import com.incedo.repository.ProfessorRepository;

/**
 * @author David Masaka
 *
 */
@SpringBootTest
class ProfessorTestJPA {

	@Autowired
	private ProfessorRepository prof;
	

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

}
