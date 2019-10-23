package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="COURSE")
public class Course {
	//STATE
	@Min(0)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="courseSequence")
	@SequenceGenerator(allocationSize=1, name="courseSequence", sequenceName="SQ_COURSE_PK")
	@Column(name="COURSE_ID")
	private int id;
	@NotEmpty // JSR303 validation
	@Column(name="COURSE_NAME")
	private String name;
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();
	@OneToMany(mappedBy = "course")
    List<CourseRegistration> registrations;
	//CONSTRUCTOR
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Course(String name) {
		super();
		this.name = name;
	}
	public Course(int id) {
		super();
		this.id = id;
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
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", students=" + students + "]";
	}	
}
