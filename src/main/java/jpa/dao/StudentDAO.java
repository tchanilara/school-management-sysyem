package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO {

	/**
	 * This method reads the student table in your database and returns the data.
	 * 
	 * @param None
	 * @return List<Student>
	 */
	List<Student> getAllStudents();

	/**
	 * This method takes a Student’s email as a String and parses the student list
	 * for a Student with that email and returns a Student Object.
	 * 
	 * @param String email
	 * @return Student
	 */
	Student getStudentByEmail(String email);

	/**
	 * This method takes two parameters: the first one is the user email and the
	 * second one is the password from the user input. Return whether or not a
	 * student was found.
	 * 
	 * @param String email
	 * @param String password
	 * @return Boolean
	 */
	Boolean validateStudent(String email, String password);

	/**
	 * This method takes a Student’s email and a Course ID. If the Student is not
	 * attending that Course, register the student for that course; otherwise not.
	 * 
	 * @param String  email
	 * @param Integer id
	 * @return void
	 */
	void registerStudentToCourse(String  email, Integer id);
	
	/**
	 * This method takes a Student’s Email as a parameter and would find all the courses a student is registered for.
	 * @param String  email
	 * @return List<Course>
	 */

	List<Course> getStudentCourses(String  email);

}
