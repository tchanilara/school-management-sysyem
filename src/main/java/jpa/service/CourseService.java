package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO {

	public List<Course> getAllCourses() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		String hql = "FROM Course";
		TypedQuery<Course> query = session.createQuery(hql, Course.class);

		List<Course> result = query.getResultList();
		session.close();
		return result;

	}

	public Course getCourseById(Integer id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		String hql = "FROM Course c WHERE c.cId = ?1 ";
		TypedQuery<Course> query = session.createQuery(hql, Course.class);

		query.setParameter(1, id);
		try {
			Course result = query.getSingleResult();
			session.close();
			return result;
		} catch (NoResultException nre) {
			return null;

		}

	}

}
