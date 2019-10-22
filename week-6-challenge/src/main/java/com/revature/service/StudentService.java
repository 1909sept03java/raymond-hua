package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Student;
import com.revature.model.Student;
import com.revature.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> allStudents() {
		return this.studentRepository.findAll();
	}

	public Student getStudentById(int id) {
		return this.studentRepository.findById(id).orElse(null);
	}

	public Student getStudentByName(String name) {
		List<Student> targets = this.studentRepository.findAll();
		Student target = null;
		for(Student i : targets) {
			if (i.getName().equals(name)) {
				target = i;
				break;
			}
		}
		return target;
	}
	
	public void addStudent(Student s) {
		this.studentRepository.save(s);
	}
	
	public void deleteStudent(Student s) {
		this.studentRepository.delete(s);
	}
	
	public void updateStudent(Student s) {
		Student target = getStudentByName(s.getName());
		if (target == null)
			this.studentRepository.save(s);
		else {
			this.studentRepository.delete(s);
			this.studentRepository.save(s);
		}
	}
}
