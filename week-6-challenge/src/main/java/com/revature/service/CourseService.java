package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Course;
import com.revature.model.Course;
import com.revature.model.Course;
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

	public Course getCourseByName(String name) {
		List<Course> targets = this.courseRepository.findAll();
		Course target = null;
		for(Course i : targets) {
			if (i.getName().equals(name)) {
				target = i;
				break;
			}
		}
		return target;
	}
	public void addCourse(Course c) {
		this.courseRepository.save(c);
	}
	
	public void deleteCourse(Course c) {
		this.courseRepository.delete(c);
	}
	
	public void updateCourse(Course s) {
		Course target = getCourseByName(s.getName());
		if (target == null)
			this.courseRepository.save(s);
		else {
			this.courseRepository.delete(s);
			this.courseRepository.save(s);
		}
	}
}
