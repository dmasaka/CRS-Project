/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class PaymentNotAprroved extends Exception {
	public String getMessage() {
		return "Payment has not been approved";
	}
}
