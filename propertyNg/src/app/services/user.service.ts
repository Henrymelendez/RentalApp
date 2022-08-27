import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'api/user';

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHtpOption(){
    let option = {
      headers: {
        Authorization: 'Basic' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return option;
  }


  index(): Observable<User[]> {

    return this.http.get<User[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("UserService.index(): Error returning index" + err)
        )
      })
    );
  }

  show(id: number): Observable<User>{
    return this.http.get<User>(this.url+"/"+id, this.getHtpOption()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(
          () => new Error("UserService.Show(): error retrieving user" + err)
        )
      })
    )
  };


  update(updateUser: User): Observable<User>{
    return this.http.put<User>(this.url+"/"+updateUser, updateUser, this.getHtpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("UserService.update(): error updating user" + err)
        )

      })
    )
  };


  destroy(id: number): Observable<User>{
    return this.http.get<User>(this.url+"/"+id, this.getHtpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("UserService.Show(): error retrieving user"+ err)
        )
      })
    )
  };

  create(user: User): Observable<User> {

    return this.http.post<User>(this.url, user, this.getHtpOption()).pipe(
      catchError((err : any) => {
        return throwError(
          () => new Error("UserService.create(): error creating User" + err)
        )
      })
    );
  };


}
