/**
 * 
 */
package com.incedo.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incedo.service.AdminService;
import com.incedo.service.ProfessorService;

/**
 * @author David Masaka
 *
 */
public class ProfessorTest {
	
	ProfessorService prof = new ProfessorService();
	AdminService adm = new AdminService();
	static int profid;
	
	@Test
	public void addProf() {
		adm.addProfessor("silly songs", "sillys", "silly12", "Say Oooh", "Larry", "Sombody", new Date(494494949));
		profid = prof.findProfessorId("sillys", "silly12");
		assertTrue(profid > 1);
	}
	
	@Test
	public void addcourse() {
		adm.addCourse("FUN101", "What is Fun", true, profid);
		assertTrue(prof.addGrade(profid, "FUN101", 115, "None"));
	}

}
