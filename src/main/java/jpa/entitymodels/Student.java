package jpa.entitymodels;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "students")
@Getter 
@Setter
public class Student {
	
	public Student(){
		
	}
	
	public Student(String sEmail, String sName, String sPass, List<StudentCourse> listCourses) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.listCourses = listCourses;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private  Integer sId;
	
	@Column(name = "sEmail")
	private  String sEmail; 
	
	@Column(name = "sName")
	private  String sName;
	
	@Column(name = "sPassword")
	private  String sPass;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private  List<StudentCourse> listCourses;

}
