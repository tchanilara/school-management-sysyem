package jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourse;

public class StudentService implements StudentDAO {
	public List<Student> getAllStudents() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		String hql = "FROM Student";
		TypedQuery<Student> query = session.createQuery(hql, Student.class);

		List<Student> result = query.getResultList();
		session.close();
		return result;

	}

	public Student getStudentByEmail(String email) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		String hql = "FROM Student s WHERE s.sEmail = :email"; // Example of HQL to get all records of user class
		TypedQuery<Student> query = session.createQuery(hql, Student.class);

		query.setParameter("email", email);
		try {
			Student result = query.getSingleResult();
			session.close();
			return result;
		} catch (NoResultException nre) {
			return null;

		}

	}

	public Boolean validateStudent(String email, String password) {
		Student student = getStudentByEmail(email);
		if ((student != null) && (password.equals(student.getSPass()))) {
			return true;
		}
		return false;

	}

	/**
	 * Return true if student is already registered to course
	 * 
	 * @param idStudent
	 * @param id
	 * @return Boolean
	 */
	private Boolean isRegistered(Integer idStudent, Integer id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		String hql = "FROM StudentCourse sc WHERE sc.sId = ?1 and sc.cId = ?2";
		TypedQuery<StudentCourse> query = session.createQuery(hql, StudentCourse.class);

		query.setParameter(1, idStudent);
		query.setParameter(2, id);
		try {
			query.getSingleResult();
			session.close();
			return true;
		} catch (NoResultException nre) {
			return false;

		}
	}

	public void registerStudentToCourse(String email, Integer id) {
		Student student = getStudentByEmail(email);
		Course course = new CourseService().getCourseById(id);
		if (student == null) {
			System.out.println(ErrorMessage.studentNotExist());
			System.exit(0);
		} else if (course == null) {
			System.out.println(ErrorMessage.courseNotExist());
			System.exit(1);
		} else if (isRegistered(student.getSId(), id)) {
			System.out.println(ErrorMessage.studentAlreadyRegistered());
			System.exit(2);
		} else {
			StudentCourse studentCourse = new StudentCourse(student.getSId(), id, student, course);

			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();

			Transaction t = session.beginTransaction();

			session.persist(studentCourse);
			t.commit();
			session.close();

		}

	}

	public List<Course> getStudentCourses(String email) {
		Student student = getStudentByEmail(email);
		if (student == null) {
			System.out.println(ErrorMessage.studentNotExist());
			return null;
		} else {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			String hql = "FROM StudentCourse sc WHERE sc.sId = ?1 ";
			TypedQuery<StudentCourse> query = session.createQuery(hql, StudentCourse.class);

			query.setParameter(1, student.getSId());
			List<StudentCourse> studentcourses = query.getResultList();
			List<Course> courses = new ArrayList<Course>();
			for(StudentCourse sc : studentcourses) {
				courses.add(sc.getCourse());
			}
			return courses;
		}

	}

}
