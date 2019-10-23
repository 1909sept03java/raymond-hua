package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="STUDENT")
public class Student {
	//STATE
	@Min(0)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="studentSequence")
	@SequenceGenerator(allocationSize=1, name="studentSequence", sequenceName="SQ_STUDENT_PK")
	@Column(name="STUDENT_ID")
	private int id;
	@NotEmpty // JSR303 validation
	@Column(name="STUDENT_NAME")
	private String name;
	@ManyToMany
	@JoinTable(
			  name = "COURSE_STUDENT", 
			  joinColumns = @JoinColumn(name = "STUDENT_ID"), 
			  inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	private List<Course> courses = new ArrayList<>();
	@OneToMany(mappedBy = "student")
	List<CourseRegistration> registrations;
	//CONSTRUCTOR
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Student(String name) {
		super();
		this.name = name;
	}
	//BEHAVIOR
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
