import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { FormGroup, FormsModule, NgForm } from '@angular/forms';
import { IUsers, UsersObj } from '../../models/Users.model';
import { DatePipe, NgIf} from '@angular/common';

const usersUrl = 'http://localhost:8090/api/v1/users/';
const myheaders = new HttpHeaders({  'Content-Type': 'application/json','X-Auth-Key' :'mysecretkey' });


@Component({
  selector: 'app-users',
  imports: [FormsModule, NgIf, DatePipe],
  templateUrl: './users.html',
  styleUrl: './users.css'
})

export class Users implements OnInit{

  private http = inject(HttpClient);

  usersList: IUsers[] = [];

  usersObj: UsersObj = new UsersObj();

  usersId: string = '';

  userAction: string ='';

  usersForm!: FormGroup;

  constructor(private cd: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getUsersInfo();
  }

  submitted = false;
    
  onSubmit(form: NgForm) {
    this.submitted = true;
    if (form.invalid) {
      console.log('User Form is invalid');
      return;
    }
    //save student
    this.saveUsers();
  }

  getUsersInfo() {

    this.http.get<IUsers[]>(usersUrl + 'fetch/all',  { headers: myheaders }).subscribe({
      next: (response) => {
        this.usersList = response;
        console.log('Users Data loaded:', response);
        this.cd.detectChanges();  // Force UI update if needed
      },
      error: (error) => {
        console.error('Error fetching users info:', error);
      },
      complete: () => {
        console.log('Users Request completed');
      }
    });
  }

  saveUsers(){ 
    
    this.http.post<UsersObj>(usersUrl + 'create', this.usersObj,  { headers: myheaders }).subscribe({ 
      next: (result: UsersObj) => {
        this.getUsersInfo(); // Refresh the list after saving
        this.onReset(); // Reset the form after saving
        console.log('Users info saved successfully.');
      }, 
      error: (error) => {
        console.error('Error saving users info:', error);
      },
      complete: () => {
        console.log('Users Request completed');
      }
    });
  }

  onReset() {
    this.submitted = false;
    this.usersObj = new UsersObj();
  }

  editUsers(userId: number) { 
    console.log("users item to edit.."+userId);
    this.http.get<any>(usersUrl + 'fetch/'+userId, {headers: myheaders}).subscribe({ 
      next: (result:any) => {
        this.usersObj = result;
        this.userAction = 'UPDATE';
        this.cd.detectChanges();  // Force UI update if needed
        console.log('Users info fetched for editing:');
      }, 
      error: (error) => {
        console.error('Error editing users info:', error);
      },
      complete: () => {
        console.log('Users Request completed');
      }
    });
  }

  deleteUsers(userId: number){
    console.log("users item to delete.."+userId);
    this.http.delete<any>(usersUrl + 'delete/'+userId, {headers: myheaders}).subscribe({ 
      next: (result:any) => {
        this.usersObj = result;
        this.cd.detectChanges();  // Force UI update if needed
        console.log('Users info fetched for editing:');
      }, 
      error: (error) => {
        console.error('Error editing users info:', error);
      },
      complete: () => {
        console.log('Users Request completed');
      }
    });

  }

  searchByUserName() {
    var userName = "*";
    if (this.usersObj.searchUser.trim()) {
      userName = this.usersObj.searchUser.trim();
    }
    console.log('user name to search :'+userName);
    this.http.get<IUsers[]>(usersUrl + 'fetch/username/'+userName).subscribe({
      next: (response) => {
        this.usersList = response;
        console.log('Users Data loaded:', response);
        this.cd.detectChanges();  // Force UI update if needed
      },
      error: (error) => {
        console.error('Error fetching users info:', error);
      },
      complete: () => {
        console.log('Users Request completed');
      }
    });
  }

}
