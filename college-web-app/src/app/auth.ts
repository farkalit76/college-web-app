import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class Auth {
  
  //private apiUrl = 'http://localhost:8090/api/login';
    private apiUrl = 'http://localhost:8090/v1/login/validate';

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string) {
    return this.http.post(`${this.apiUrl}`, { username, password }).pipe(
      tap((res: any) => {
        if (res?.token) {
          localStorage.setItem('authKey', res.token);
        }
      })
    );
  }

  getHeader() {
    return { 'X-Auth-Key': localStorage.getItem('authKey') || '' };
  }

  logout() {
    localStorage.removeItem('authKey');
    this.router.navigate(['/']);
  }

  isAuthenticated() {
    return !!localStorage.getItem('authKey');
  }
}