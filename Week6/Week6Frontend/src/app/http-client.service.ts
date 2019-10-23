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

export class HttpClientService {

  constructor() { }
}
