import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { PropertyType } from '../models/property-type';

@Injectable({
  providedIn: 'root'
})
export class PropertyTypeService {

  private url = environment.baseUrl +'api/propertyType'

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOption(){

    let option = {
      headers: {
        Authorization: 'Basic' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    }
    return option;
  }

  index(): Observable<PropertyType[]>{

    return this.http.get<PropertyType[]>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("PropertyService.index(): Error return a Property Type.")
        )
      })
    );
  };

  show(id: number): Observable<PropertyType> {
    return this.http.get<PropertyType>(this.url+'/'+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("PropertyType.Show(): error retrieving ProperyType"+ err)
        )
      })
    );
  };


destroy(id: number): Observable<PropertyType> {

  return this.http.delete<PropertyType>(this.url+"/"+id, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyType.destroy(): error deleting PropetyType"+ err)
      )

    })
  );
};

create(propType: PropertyType): Observable<PropertyType>{

  return this.http.post<PropertyType>(this.url, propType, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyTypeService.create(): error creating PropertyType" + err)
      )
    })
  );
};


update(propertytype: PropertyType): Observable<PropertyType>{
  return this.http.put<PropertyType>(this.url+"/"+propertytype.id, propertytype, this.getHttpOption()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error("PropertyType.update(): error updating PropertyType"+ err)
      )
    })
  );
};

}
