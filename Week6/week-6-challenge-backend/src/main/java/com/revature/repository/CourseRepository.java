package com.revature.repository;

import org.springframework.stereotype.Repository;

import com.revature.model.Course;
import com.revature.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
}
