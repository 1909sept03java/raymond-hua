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
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Student;
import com.revature.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	private StudentService service;
	
	@Autowired
	public void setStudentService(StudentService service) {
		this.service = service;
	}
	
	//READ
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAll() {
		return new ResponseEntity<>(this.service.allStudents(), HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		Student s = this.service.getStudentById(id);
		if (s == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
	}
	//CREATE
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addStudent(@Valid @RequestBody Student s) {
		ResponseEntity<String> resp = null;
		try {
			this.service.addStudent(s);
			resp = new ResponseEntity<>("STUDENT CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<>("FAILED TO CREATE STUDENT", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	//UPDATE
	@RequestMapping(method=RequestMethod.PUT) 
	public ResponseEntity<String> updateStudent(@RequestBody Student s) {
		ResponseEntity<String> resp = null;
			try {
				this.service.updateStudent(s);
				resp = new ResponseEntity<>("STUDENT UPDATED SUCCESSFULLY", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<>("FAILED TO UPDATE STUDENT", HttpStatus.BAD_REQUEST);
			}
		return resp;
	}
	//DELETE
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteStudent(@RequestBody Student s) {
		ResponseEntity<String> resp = null;
			try {
				this.service.deleteStudent(s);
				resp = new ResponseEntity<>("STUDENT DELETED SUCCESSFULLY", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<>("FAILED TO DELETE STUDENT", HttpStatus.BAD_REQUEST);
			}
		return resp;
	}
}
