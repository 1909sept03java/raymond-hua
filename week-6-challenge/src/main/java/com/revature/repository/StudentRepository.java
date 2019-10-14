package com.revature.repository;

import org.springframework.stereotype.Repository;

import com.revature.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
