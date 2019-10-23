import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class Student{
  constructor(
    public student_id:number,
    public name:string,
  ) {}
}
export class Course{
  constructor(
    public course_id:number,
    public name:string,
  ) {}
}
export class Registration{
  constructor(
    public student:Student,
    public course:Course,
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) { }

  getStudents()
  {
    console.log("Get all students");
    console.log(this.httpClient.get<Student[]>('http://localhost:8083/student/all'));
    return this.httpClient.get<Student[]>('http://localhost:8083/student/all');
  }
  getCourses()
  {
    console.log("Get all courses");
    console.log(this.httpClient.get<Student[]>('http://localhost:8083/course/all'));
    return this.httpClient.get<Student[]>('http://localhost:8083/course/all');
  }
  getCourseRegistrations()
  {
    console.log("Get all course registrations");
    console.log(this.httpClient.get<Student[]>('http://localhost:8083/cs/all'));
    return this.httpClient.get<Student[]>('http://localhost:8083/cs/all');
  }
}
