import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgZone } from '@angular/core';
import { Auth } from '../../auth';

const usersUrl = 'http://localhost:8090/api/v1/users/login';
const loginUrl = 'http://localhost:8090/api/v1/login/validate';
//const myheaders = new HttpHeaders({ 'MyAPP' :'vip_college' });



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
   username = '';
  password = '';
  error = '';

  constructor(private auth: Auth, private router: Router, private zone: NgZone) {}

  onLogin() {
    
    console.log(' Attempting login with:', this.username);

    this.auth.login(this.username, this.password).subscribe({
      next: (response) => {
        if(response.status == 'success'){
          console.log('Login response success:', response);
            this.router.navigate(['/student-page']).then((navigated) => {
            console.log(' Navigation result:', navigated);
          });
        }else{
          console.log('Login response failed:', response);
          this.error = 'Invalid username or password';
        }
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.error = 'Invalid credentials';
      }
    });
  }
}


// @Component({
//   selector: 'app-login',
//    imports: [FormsModule, NgIf],
//   templateUrl: './login.html',
//   styleUrl: './login.css'
// })
//export class Login implements OnInit{
//    private http = inject(HttpClient);

//    private router = inject(Router);

//   usersList: ILogin[] = [];

//   loginObj: LoginObj = new LoginObj();

//   isValid: boolean = false;

//   errorMsg: string ='';

//   loginForm!: FormGroup;

//   constructor(private cd: ChangeDetectorRef) {}

//     ngOnInit(): void {

//   }

//   submitted = false;
    
//   onSubmit(form: NgForm) {
//     this.submitted = true;
//     if (form.invalid) {
//       console.log('Login Form is invalid');
//       return;
//     }
//     //save student
//     this.loginUser();
//   }

//   loginUser(){
    
//     this.http.post<LoginObj>(loginUrl, this.loginObj, {headers:myheaders}
//       ).subscribe({ 
//       next: (result: LoginObj) => {
//         console.log('validation response: ', result);
//         if (result && result.validation === true) { 
//           this.isValid = true;
//           console.log('User logged in successfully.');
//           this.router.navigate(['/student-page']);
//         } else {
//           this.errorMsg ='Login failed! Please enter correct username and password.';
//           console.log('Invalid user credentials.');
//           this.router.navigate(['/login-page']);
//         }
//       }, 
//       error: (error) => {
//         console.error('Error login users :', error);
//       },
//       complete: () => {
//         console.log('Login Request completed');
//       }
//     });
//   }
// }
