package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Course;
import com.revature.model.Student;
import com.revature.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> allCourses() {
		return this.courseRepository.findAll();
	}

	public Course getCourseById(int id) {
		return this.courseRepository.findById(id).orElse(null);
	}

	public void addCourse(Course c) {
		this.courseRepository.save(c);
	}
	
	public void deleteCourse(Course c) {
		this.courseRepository.delete(c);
	}
	
	public void updateCourse(Course c) {
		this.courseRepository.delete(this.courseRepository.findById(c.getId()).orElse(null));
		this.courseRepository.save(c);
	}
	
	public void addStudent(Course c, Student s) {
		Course target = this.courseRepository.findById(c.getId()).orElse(null);
		List<Student> list = target.getStudents();
		list.add(s);
		target.setStudents(list);
		this.courseRepository.delete(this.courseRepository.findById(c.getId()).orElse(null));
		this.courseRepository.save(target);
	}
}
