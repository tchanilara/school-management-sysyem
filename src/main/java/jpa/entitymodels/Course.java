package jpa.entitymodels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "courses")
@Getter 
@Setter
public class Course {

	public Course() {

	}

	public Course(String cInstructor, String cName) {
		this.cInstructor = cInstructor;
		this.cName = cName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private  Integer cId;
	
	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cInstructor=" + cInstructor + ", cName=" + cName + "]";
	}

	@Column(name = "cInstructorName")
	private  String cInstructor;
	
	@Column(name = "cName")
	private  String cName;
}
