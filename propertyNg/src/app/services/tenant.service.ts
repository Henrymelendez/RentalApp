import { Tenant } from './../models/tenant';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { identifierName } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class TenantService {

  private url = environment.baseUrl + "api/tenant"

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOption(){
    let option = {
      headers: {
        Authorization: 'Basic'+ this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return option;
  }


index(): Observable<Tenant[]>{
  return this.http.get<Tenant[]>(this.url, this.getHttpOption()).pipe(
    catchError((err: any) => {
      return throwError(
        () => new Error("TenantServices.index(): Error retrieving Tenant"+ err)
      )
    })
  )
};

create(tenant: Tenant): Observable<Tenant>{
  return this.http.post<Tenant>(this.url, tenant, this.getHttpOption()).pipe(
    catchError((err: any) => {
      return throwError(
        () => new Error("TenantService.create(): error creating tenant"+ err)
      )
    })
  )
};

show(id: number): Observable<Tenant>{
  return this.http.get<Tenant>(this.url+"/"+id, this.getHttpOption()).pipe(
    catchError((err: any) => {
      return throwError(
        () => new Error("TenntServices.show(): error retieving Tenant" + err)
      )
    })
  )
};

update(tenant: Tenant): Observable<Tenant>{
  return this.http.put<Tenant>(this.url+"/"+tenant.id, tenant, this.getHttpOption()).pipe(
    catchError((err: any) => {
      return throwError(
        () => new Error("TenantService.update(): error updating Tenant"+ err)
      )
    })
  );
};


destroy(id: number): Observable<Tenant>{
  return this.http.delete<Tenant>(this.url+"/"+id, this.getHttpOption()).pipe(
    catchError((err: any) => {
      return throwError(
        () => new Error("TenantService.destroy(): error destroying tenant" + err)
      )
    })
  )
};


}
