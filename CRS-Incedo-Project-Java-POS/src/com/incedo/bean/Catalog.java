package com.incedo.bean;

public class Catalog {
	
	//this will need to be changed
	private int catalogId;
	private String name;
	private Course[] courses = {new Course(), new Course(), new Course()};
	
	/**
	 * getter for catalogid
	 * @return int
	 */
	public int getCatalogId() {
		return catalogId;
	}

	/** setter for catalogid
	 * @param catalogId
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course[] getCourses() {
		return courses;
	}

	public void setCourses(Course[] courses) {
		this.courses = courses;
	}
	
	
}
