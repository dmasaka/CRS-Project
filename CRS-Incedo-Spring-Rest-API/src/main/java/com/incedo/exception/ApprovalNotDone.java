/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class ApprovalNotDone extends Exception {
	public String getMessage() {
		return "Need Approval from Admin";
	}
}
