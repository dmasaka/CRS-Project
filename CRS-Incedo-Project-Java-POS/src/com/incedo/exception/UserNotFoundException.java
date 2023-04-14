/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class UserNotFoundException extends Exception {
	
	public String getMessage() {
		return "User was not found";
	}
}
