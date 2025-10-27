import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { IStudent, StudentObj } from '../../models/Student.model';
import { FormGroup, FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { NgIf} from '@angular/common';

const studentUrl = 'http://localhost:8090/api/v1/student/';
const myheaders = new HttpHeaders({  'Content-Type': 'application/json','X-Auth-Key' :'mysecretkey' });

@Component({
  selector: 'app-student',
  imports: [FormsModule, RouterLink, NgIf],
  templateUrl: './student.html',
  styleUrl: './student.css'
})
export class Student implements OnInit{

  private http = inject(HttpClient);

  studentList: IStudent[] = [];

  studentObj: StudentObj = new StudentObj();

  studentId: string = '';

  studentForm!: FormGroup;

  constructor(private cd: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getStudentInfo();
  }

  submitted = false;
    
  onSubmit(form: NgForm) {
    this.submitted = true;
    if (form.invalid) {
      console.log('Form is invalid');
      return;
    }
    //save student
    this.saveStudent();
  }

  getStudentInfo() {

    this.http.get<IStudent[]>(studentUrl + 'fetch/all', {headers: myheaders}).subscribe({
      next: (response) => {
        this.studentList = response;
        console.log('Student Data loaded:', response);
        this.cd.detectChanges();  // Force UI update if needed
      },
      error: (error) => {
        console.error('Error fetching student info:', error);
      },
      complete: () => {
        console.log('Request completed');
      }
    });
  }

  saveStudent(){ 
    this.http.post<StudentObj>(studentUrl + 'create', this.studentObj, {headers: myheaders}).subscribe({ 
      next: (result: StudentObj) => {
        this.getStudentInfo(); // Refresh the list after saving
        this.onReset(); // Reset the form after saving
        console.log('Student info saved successfully.');
      }, 
      error: (error) => {
        console.error('Error saving student info:', error);
      },
      complete: () => {
        console.log('Request completed');
      }
    });
  }

  onReset() {
    this.submitted = false;
    this.studentObj = new StudentObj();
  }

  editStudent(stdId: number) { 
    console.log("student item to edit..");
    console.log(stdId);
    this.http.get<any>(studentUrl + 'fetch/'+stdId, {headers: myheaders}).subscribe({ 
      next: (result:any) => {
        this.studentObj = result;
        this.cd.detectChanges();  // Force UI update if needed
        console.log('Student info fetched for editing:');
      }, 
      error: (error) => {
        console.error('Error editing student info:', error);
      },
      complete: () => {
        console.log('Request completed');
      }
    });
  }

  addAddress(stdId: number) {
    console.log("Adding address for student ID:", stdId);
    this.http.get<any>(studentUrl + 'fetch/'+stdId, {headers: myheaders}).subscribe({ 
      next: (result:any) => {
        this.studentObj = result;
        this.studentId = this.studentObj.studentId;
        console.log("Student ID for address:", this.studentId);
      }, 
      error: (error) => {
        console.error('Error editing student info:', error);
      },
      complete: () => {
        console.log('Request completed');
      }
    });
  }
}
