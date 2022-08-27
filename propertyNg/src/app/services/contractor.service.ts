import { Maintenance } from './../models/maintenance';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Contractor } from '../models/contractor';

@Injectable({
  providedIn: 'root'
})
export class ContractorService {

  private url = environment.baseUrl + "api/maintenance"

  constructor(private http: HttpClient, private auth: AuthService) { }


  getHttpOption(){
    let option = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return option;
  }


  index(): Observable<Contractor[]>{
    return this.http.get<Contractor[]>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("MaintenanceService.index(): error retrieving a list of contractor" + err)
        )
      })
    )
  };


  create(contractor: Contractor): Observable<Contractor>{
    return this.http.post<Contractor>(this.url+"/"+contractor.id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("ContractorService.create(): error updating contractor" + err)
        )
      })
    )
  };



  show(id: number): Observable<Contractor>{
    return this.http.get<Contractor>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("ContractorService.show(): error retieving a Contractor" + err)
        )
      })
    )
  };


  update(contractor : Contractor): Observable<Contractor>{
    return this.http.put<Contractor>(this.url+"/"+contractor.id, contractor, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("ContractorService.update(): error updating a contractor " + err)
        )
      })
    );
  };



  destroy(id: number): Observable<Contractor>{
    return this.http.delete<Contractor>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("ContractorService.destroy(): error destroying a contractor" + err)
        )
      })
    )
  };

}
