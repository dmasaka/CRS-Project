package com.incedo.exception;

public class CourseDoesNotExistException extends Exception {
	public String getMessage() {
		return "Course Entered does not exist";
	}
}
