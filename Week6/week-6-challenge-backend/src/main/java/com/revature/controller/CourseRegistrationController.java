package com.revature.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Course;
import com.revature.model.CourseRegistration;
import com.revature.model.Student;
import com.revature.service.CourseRegistrationService;

@RestController
@RequestMapping(value = "/cs")
public class CourseRegistrationController {

	private CourseRegistrationService service;
	
	@Autowired
	public void setCourseRegistrationService(CourseRegistrationService service) {
		this.service = service;
	}
	
	//READ
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<CourseRegistration>> getAll() {
		return new ResponseEntity<>(this.service.allCourseRegistrations(), HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CourseRegistration> getCourseRegistrationById(@PathVariable int id) {
		CourseRegistration s = this.service.getCourseRegistrationById(id);
		if (s == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ResponseEntity<List<CourseRegistration>> getCourseRegistrationByStudentId(@RequestParam int id) {
		List<CourseRegistration> s = this.service.getCourseRegistrationByStudentId(id);
		if (s == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
	}
	//CREATE
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addCourseRegistration(@RequestParam int student_id, @RequestParam int course_id) {
		ResponseEntity<String> resp = null;
		try {
			this.service.addCourseRegistration(new CourseRegistration(new Student(student_id, ""), new Course(course_id, "")));
			resp = new ResponseEntity<>("COURSE REGISTRATION CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO CREATE COURSE REGISTRATION", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	//UPDATE
	@RequestMapping(method=RequestMethod.PUT) 
	public ResponseEntity<String> updateCourseRegistration(@RequestParam int student_id, @RequestParam int course_id) {
		ResponseEntity<String> resp = null;
			try {
				this.service.updateCourseRegistration(new Student(student_id, ""), new Course(course_id,""));
				resp = new ResponseEntity<>("COURSE REGISTRATION UPDATED SUCCESSFULLY", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<>("FAILED TO UPDATE COURSE REGISTRATION", HttpStatus.BAD_REQUEST);
			}
		return resp;
	}
	//DELETE
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteCourseRegistration(@RequestParam int student_id, @RequestParam int course_id) {
		List<CourseRegistration> s = this.service.getCourseRegistrationByStudentId(student_id);
		CourseRegistration target = null;
		for (CourseRegistration i : s) {
			if (i.getCourse().getId() == course_id)
				target = i;
		}
		ResponseEntity<String> resp = null;
			try {
				this.service.deleteCourseRegistration(target);
				resp = new ResponseEntity<>("COURSE REGISTRATION DELETED SUCCESSFULLY", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<>("FAILED TO DELETE COURSE REGISTRATION", HttpStatus.BAD_REQUEST);
			}
		return resp;
	}
}
