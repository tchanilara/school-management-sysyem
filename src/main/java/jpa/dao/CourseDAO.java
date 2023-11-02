package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;

public interface CourseDAO {
	/**
	 * This method reads the course table in your database and returns the data.
	 * 
	 * @param None
	 * @return List<Course>
	 */
	List<Course> getAllCourses();

}
