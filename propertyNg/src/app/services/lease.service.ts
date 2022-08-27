import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { Lease } from '../models/lease';

@Injectable({
  providedIn: 'root'
})
export class LeaseService {

  private url = environment.baseUrl + "api/lease";

  constructor(private http: HttpClient, private auth : AuthService) { }

  getHttpOption(){

    let option = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return option;
  }


  index(): Observable<Lease[]>{
    return this.http.get<Lease[]>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("LeaseService.index(): Error retrieving a list of lease" + err)
        )
      })
    )
  };

  create(lease: Lease): Observable<Lease>{
    return this.http.post<Lease>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("LeaseService.create(): error creating a lease" + err)
        )
      })
    )
  };


  show(id: number): Observable<Lease>{
    return this.http.get<Lease>(this.url+"/"+id , this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("LeaseService.show(): error retrieving a lease"+ err)
        )
      })
    )
  };

  update(lease: Lease): Observable<Lease>{
    return this.http.put<Lease>(this.url+"/"+lease.id, lease, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);

        return throwError(
          () => new Error("LeaseService.update(): error updating lease" + err)
        )
      })
    )
  };

  destroy(id: number): Observable<Lease>{
    return this.http.delete<Lease>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("LeaseService.destroy(): error destroying a lease"+ err)
        )
      })
    )
  };



}
