package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.ErrorMessage;
import jpa.service.StudentService;

public class SMSRunner {


	private Scanner scan;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		scan = new Scanner(System.in);
		courseService = new CourseService();
		studentService = new StudentService();
	}

	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			System.out.println(ErrorMessage.goodBye());
			break;

		default:

		}
	}

	private int menu1() {
		System.out.println("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");

		return scan.nextInt();
	}

	private boolean studentLogin() {
		
		System.out.println("Enter your email address: ");
		String email = scan.next();
		System.out.println("Enter your password: ");
		String password = scan.next();
		if(studentService.validateStudent(email,password)) {
			this.currentStudent = studentService.getStudentByEmail(email);
			return true;
		}
		System.out.println(ErrorMessage.studentNotExist());
		return false;
	}

	private void registerMenu() {
		System.out.println("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");

		switch (scan.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			System.out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				System.out.println(course);
			}
			
			System.out.print("\nEnter Course Number: ");
			int id = scan.nextInt();
			
			studentService.registerStudentToCourse(currentStudent.getSEmail(), id);
			System.out.println("MyClasses");
			for (Course course : studentService.getStudentCourses(currentStudent.getSEmail())) {
				System.out.println(course);
			}
			break;
		case 2:
		default:
			System.out.println(ErrorMessage.goodBye());
		}
	}

}
