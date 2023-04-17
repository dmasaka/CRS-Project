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
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incedo.controller.ProfessorController;
import com.incedo.entity.Professor;
import com.incedo.repository.ProfessorRepository;

/**
 * @author David Masaka
 *
 */
@ExtendWith(MockitoExtension.class)
class ProfessorTestRest {
	
	@InjectMocks
	ProfessorController prof;
	
	@Mock
	ProfessorRepository profrep;

	/**
	 * this is a test for adding/updating fetching and deleting 
	 */
	@Test
	public void ProfessorAddUpdateTest() {
		//save a new Professor
		List<Professor> init = new ArrayList<>();
		Professor prof1 = new Professor("Per Son", "PerS", "Per12", "Place", "Depart", "Design", new Date(93939393));
		init.add(prof1);
		prof.addProfessor(prof1);
		when(profrep.findAll()).thenReturn(init);
		System.out.println(prof.getAllProfessors());
		assertTrue( prof.getAllProfessors().size() > 0, "Professor was added");
		when(profrep.findAll()).thenReturn(init);
		List<Professor> unap = prof.getAllProfessors();
		Professor prof2 = unap.stream().filter(st -> st.getUsername().equals(prof1.getUsername())).findFirst().get();
		assertNotNull(prof2, "added to list");
		Map<String, String> cred = new TreeMap<>();
		cred.put("username", "PerS");
		cred.put("password", "Per12");
		when(profrep.findProfessorByUsername(Mockito.anyString())).thenReturn(prof1);
		Professor prof3 = prof.login(cred);
		assertNotNull(prof3, "Logged in");
		prof.deleteProfessor(prof3.getUserId());
		init.remove(0);
		when(profrep.findAll()).thenReturn(init);
		assertTrue(prof.getAllProfessors().size() == 0, "deleted Professor");
	}

}
