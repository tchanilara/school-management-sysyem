package jpa.service;

public class ErrorMessage {
	public static String studentNotExist() {
		return "This student doesn't exist";
	}

	public static String courseNotExist() {
		return "This course doesn't exist";
	}
	public static String studentAlreadyRegistered() {
		return "You are already registered to that course";
	}
	public static String goodBye() {
		return "Goodbye!";
	}

}
