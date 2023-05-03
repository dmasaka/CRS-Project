/**
 * 
 */
package com.incedo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incedo.entity.Payment;

/**
 * @author David Masaka
 *
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query("select p from Payment p where p.studentId = ?1")
	Payment findPaymentByStudent(int studentid);
}
