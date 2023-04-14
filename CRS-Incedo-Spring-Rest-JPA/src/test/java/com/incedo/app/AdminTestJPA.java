/**
 * 
 */
package com.incedo.app;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.incedo.entity.Admin;
import com.incedo.repository.AdminRepository;

/**
 * @author David Masaka
 *
 */
@SpringBootTest
public class AdminTestJPA {
	
	@Autowired
	private AdminRepository adm;

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

}
