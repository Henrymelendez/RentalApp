import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Property } from '../models/property';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  private url = environment.baseUrl + "api/property";

  constructor(private http: HttpClient, private auth: AuthService) { }


  getHttpOption(){

    let option = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
  };
  return option;
};


index(): Observable<Property[]>{

  return this.http.get<Property[]>(this.url, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyService.index(): error retrieving list of Property" + err)
      )
    })
  )
};

create(property: Property): Observable<Property>{
  return this.http.post<Property>(this.url, property, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyService.create(): error creating a property")
      )
    })
  );
}

show(id: number): Observable<Property>{
  return this.http.get<Property>(this.url+"/"+id, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyService.show(): error retreiving a Property" + err)
      )
    })
  )
}

update(property: Property): Observable<Property>{
  return this.http.put<Property>(this.url+"/"+property.id, property, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyService.update(): error retrieving a Property")
      )
    })
  )
};

destroy(id: number): Observable<Property>{
  return this.http.delete<Property>(this.url+"/"+id, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyService.destroy(): error destroying a property")
      )
    })
  )
};




}
