package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table (name="COURSE_REGISTRATION")
public class CourseRegistration {
 
	@Min(0)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="csSequence")
	@SequenceGenerator(allocationSize=1, name="csSequence", sequenceName="SQ_CS_PK")
	@Column(name="CS_ID")
    int id;
 
    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    Student student;
 
    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    Course course;

	public CourseRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseRegistration(int id, Student student, Course course) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
	}

	public CourseRegistration(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + id;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		CourseRegistration other = (CourseRegistration) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseRegistration [id=" + id + ", student=" + student + ", course=" + course + "]";
	}

 
}
