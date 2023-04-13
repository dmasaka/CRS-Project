/**
 * 
 */
package com.incedo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author David Masaka
 *
 */
@Entity
public class CourseRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int courseregid;
	private String coursecode;
	private int studentid;
	private String grade;
	public CourseRegistration() {};
	public CourseRegistration(String coursecode, int studentid, String grade) {
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
