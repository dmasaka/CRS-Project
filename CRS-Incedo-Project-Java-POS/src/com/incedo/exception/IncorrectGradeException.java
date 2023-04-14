/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class IncorrectGradeException extends Exception {
	public String getMessage() {
		return "Grade that was entered is not valid";
	}
}
