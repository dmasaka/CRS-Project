/**
 * 
 */
package com.incedo.bean;

/**
 * @author David Masaka
 *
 */
public class CourseRegistration {
	private int courseregid;
	private String coursecode;
	private int studentid;
	private String grade;
	public CourseRegistration(int courseregid, String coursecode, int studentid, String grade) {
		this.courseregid = courseregid;
		this.coursecode = coursecode;
		this.studentid = studentid;
		this.grade = grade;
	}
	public int getCourseregid() {
		return courseregid;
	}
	public void setCourseregid(int courseregid) {
		this.courseregid = courseregid;
	}
	public String getCoursecode() {
		return coursecode;
	}
	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
