/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class WrongProfessorException extends Exception {
	public String getMessage() {
		return "Professor does not have the authority to changes grades in this class";
	}
}
