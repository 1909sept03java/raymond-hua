package com.revature.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Course;
import com.revature.service.CourseService;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

	private CourseService service;
	
	@Autowired
	public void setCourseService(CourseService service) {
		this.service = service;
	}
	
	//READ
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> getAll() {
		return new ResponseEntity<>(this.service.allCourses(), HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Course> getFlashcardById(@PathVariable int id) {
		Course s = this.service.getCourseById(id);
		if (s == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
	}
	//CREATE
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addFlashcard(@Valid @RequestBody Course s) {
		ResponseEntity<String> resp = null;
		try {
			this.service.addCourse(s);;
			resp = new ResponseEntity<>("COURSE CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO CREATE COURSE", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	//UPDATE
	@RequestMapping(method=RequestMethod.PUT) 
	public ResponseEntity<String> updateFlashcard(@RequestBody Course s) {
		ResponseEntity<String> resp = null;
			try {
				this.service.updateCourse(s);;
				resp = new ResponseEntity<>("COURSE UPDATED SUCCESSFULLY", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<>("FAILED TO UPDATE COURSE", HttpStatus.BAD_REQUEST);
			}
		return resp;
	}
	//DELETE
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteFlashcard(@RequestBody Course s) {
		ResponseEntity<String> resp = null;
			try {
				this.service.deleteCourse(s);
				resp = new ResponseEntity<>("COURSE DELETED SUCCESSFULLY", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<>("FAILED TO DELETE COURSE", HttpStatus.BAD_REQUEST);
			}
		return resp;
	}
}
