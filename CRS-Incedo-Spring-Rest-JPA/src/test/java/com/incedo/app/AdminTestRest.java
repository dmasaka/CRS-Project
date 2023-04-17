/**
 * 
 */
package com.incedo.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.incedo.controller.AdminController;
import com.incedo.entity.Admin;
import com.incedo.repository.AdminRepository;

/**
 * @author David Masaka
 *
 */
@ExtendWith(MockitoExtension.class)
class AdminTestRest {
	
	@InjectMocks
	AdminController adm;
	
	@Mock
	AdminRepository admrep;

	/**
	 * this is a test for adding/updating fetching and deleting 
	 */
	@Test
	public void AdminAddUpdateTest() {
		//save a new Admin
		List<Admin> init = new ArrayList<>();
		Admin adm1 = new Admin("Per Son", "PerS", "Per12", "Place", new Date(222222222));
		init.add(adm1);
		adm.addAdmin(adm1);
		when(admrep.findAll()).thenReturn(init);
		assertTrue( adm.getAllAdmins().size() > 0, "Admin was added");
		when(admrep.findAll()).thenReturn(init);
		List<Admin> unap = adm.getAllAdmins();
		Admin adm2 = unap.stream().filter(st -> st.getUsername().equals(adm1.getUsername())).findFirst().get();
		assertNotNull(adm2, "added to list");
		Map<String, String> cred = new TreeMap<>();
		cred.put("username", "PerS");
		cred.put("password", "Per12");
		when(admrep.findAdminByUsername(Mockito.anyString())).thenReturn(adm1);
		Admin adm3 = adm.login(cred);
		assertNotNull(adm3, "Logged in");
		adm.deleteAdmin(adm3.getUserId());
		init.remove(0);
		when(admrep.findAll()).thenReturn(init);
		assertTrue(adm.getAllAdmins().size() == 0, "deleted Admin");
	}

}
