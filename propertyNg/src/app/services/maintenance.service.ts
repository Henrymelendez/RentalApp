import { Maintenance } from './../models/maintenance';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {

  private url = environment.baseUrl +"api/maintenance"

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOption(){
    let option = {
      headers: {
        Authorization: 'Basic'+ this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return option;
  };


  index(): Observable<Maintenance[]>{
    return this.http.get<Maintenance[]>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);

        return throwError(
          () => new Error("MaintenanceService.index(): Error return a Maintanace" + err)
         )
      })
    );
  };


  show(id: number): Observable<Maintenance>{
    return this.http.get<Maintenance>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("MaintenanceService.show(): Error retrieving Maintenance" + err)
        )
      })
    );
  };


  create(maintenance: Maintenance): Observable<Maintenance>{
    return this.http.post<Maintenance>(this.url+"/"+maintenance.id,maintenance, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("MaintenanceService.create(): Error creating Maintenance " + err)
        )
      })
    );
  };

  update(maint: Maintenance): Observable<Maintenance>{
    return this.http.put<Maintenance>(this.url+"/"+maint.id, maint, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("MaintenanceService.update(): Error updating Maintenance" + err)
        )

      })
    );
  };


  destroy(id: number): Observable<Maintenance>{
    return this.http.delete<Maintenance>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("MaintenanceService.destroy(): Error destroy Maintanance" + err)
        )
      })
    );
  };




}
