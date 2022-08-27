import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { catchError, Observable, tap,throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.baseUrl;

  constructor(private http: HttpClient) { }


  login(userName: string, password: string ): Observable<User> {
    const credentials = this.generateBasicAuthCredentials(userName, password);
    const httpOptions = {
      headers: new HttpHeaders ({
        'Authoriztion': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    return this.http.get<User>(this.url + 'authenticate', httpOptions).pipe(
      tap((newUser) => {
        localStorage.setItem('credentials', credentials);
        if(newUser != null && newUser.role != null){
          localStorage.setItem('user', newUser.role.toString());
        }
        return newUser;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("AuthService.login(): error logging user.")
        );
      })
    );
  };


  register(user: User): Observable<User> {

    return this.http.post<User>(this.url + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AuthService.register(): error registering user.')
        );
      })
    );
  }

  // check user role
  getLoggedInUser(): Observable<User> {
    if (! this.checkLogin()) {
      return throwError(
        () => { new Error('Not logged in.')}
      )
    }
    let httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.getCredentials(),
        'X-Requested-with': 'XMLHttpRequest'
      }
    };
    return this.http.get<User>(environment.baseUrl+'authenticate', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'UserService.getUserById(): error retrieving user: ' + err
            )
        );
      })
    );
  };




  logout(): void {
    localStorage.removeItem('credentials');
    localStorage.removeItem('user');
  }

  checkLogin(): boolean {
    if(localStorage.getItem('credentials')){
      return true;
    }else {
      return false;
    }
  }

  generateBasicAuthCredentials(username: string, password: string): string {
    return btoa(`${username}:${password}`);
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }

  isAdmin(): boolean {
    let item = localStorage.getItem('user');

    if(item === "admin"){
      return true;
    } else {

      return false;
    }
  }

}
