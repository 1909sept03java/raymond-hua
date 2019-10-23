package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Course;
import com.revature.model.CourseRegistration;
import com.revature.model.Student;
import com.revature.repository.CourseRegistrationRepository;

@Service
public class CourseRegistrationService {

	private CourseRegistrationRepository csRepository;

	@Autowired
	public CourseRegistrationService(CourseRegistrationRepository csRepository) {
		this.csRepository = csRepository;
	}

	public List<CourseRegistration> allCourseRegistrations() {
		return this.csRepository.findAll();
	}

	public CourseRegistration getCourseRegistrationById(int id) {
		return this.csRepository.findById(id).orElse(null);
	}

	public CourseRegistration getCourseRegistrationBySCId(Student s, Course c) {
		List<CourseRegistration> targets = this.csRepository.findAll();
		CourseRegistration target = null;
		for (CourseRegistration i : targets) {
			if (i.getStudent().getId() ==s.getId() && i.getCourse().getId() == c.getId()) {
				target = i;
				break;
			}
		}
		return target;
	}

	public void addCourseRegistration(CourseRegistration s) {
		this.csRepository.save(s);
	}

	public void deleteCourseRegistration(CourseRegistration s) {
		this.csRepository.delete(s);
	}

	public void updateCourseRegistration(Student s, Course c) {
		CourseRegistration target = getCourseRegistrationBySCId(s, c);
		if (target == null)
			this.csRepository.save(new CourseRegistration(s,c));
		else {
			this.csRepository.delete(target);
			this.csRepository.save(target);
		}
	}

	public List<CourseRegistration> getCourseRegistrationByStudentId(int id) {
		List<CourseRegistration> target = this.csRepository.findAll();		
		List<CourseRegistration> results = new ArrayList<>();;
		for(CourseRegistration i : target) {
			if(i.getStudent().getId() == id) {
				results.add(i);
			}
		}
		return results;
	}
}
