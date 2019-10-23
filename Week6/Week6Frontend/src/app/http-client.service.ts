import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class Student {
  constructor(
    public student_id: number,
    public name: string,
  ) { }
}
export class Course {
  constructor(
    public course_id: number,
    public name: string,
  ) { }
}
export class Registration {
  constructor(
    public student: Student,
    public course: Course,
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient: HttpClient
  ) { }

  //1)A student can select themselves from a roster
  getStudents() {
    console.log("Get all students");
    console.log(this.httpClient.get<Student[]>('http://localhost:8083/student/all'));
    return this.httpClient.get<Student[]>('http://localhost:8083/student/all');
  }
  //2)A student can view all courses
  getCourses() {
    console.log("Get all courses");
    console.log(this.httpClient.get<Student[]>('http://localhost:8083/course/all'));
    return this.httpClient.get<Student[]>('http://localhost:8083/course/all');
  }
  getCourseRegistrations() {
    console.log("Get all course registrations");
    console.log(this.httpClient.get<Student[]>('http://localhost:8083/cs/all'));
    return this.httpClient.get<Student[]>('http://localhost:8083/cs/all');
  }
  //3)and courses in which they are enrolled
  getEnrolledCourses(student_id){
    let params = new HttpParams();
    console.log("Enrolled courses");
    console.log(student_id);
    params = params.append('id', student_id);
    return this.httpClient.get('http://localhost:8083/cs/student',{params:params});
  }
  //4)A student can modify their schedule
  addCourse(student_id, course_id){
    let params = new HttpParams();
    console.log("Adding course");
    console.log("Student: " + student_id + ", Course: " + course_id);
    params = params.append("student_id", student_id);
    params = params.append("course_id", course_id);
    return this.httpClient.put('http://localhost:8083/cs',{params:params});
  }
  //4)A student can modify their schedule
  deleteCourse(student_id, course_id){
    let params = new HttpParams();
    console.log("Deleting course");
    console.log("Student: " + student_id + ", Course: " + course_id);
    params = params.append("student_id", student_id);
    params = params.append("course_id", course_id);
    return this.httpClient.delete('http://localhost:8083/cs',{params:params});
  }
}
