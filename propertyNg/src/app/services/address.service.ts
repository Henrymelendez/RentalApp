import { Address } from './../models/address';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private url = environment.baseUrl + "api/address"

  constructor(private http : HttpClient, private auth : AuthService) { }


  getHttpOption() {
    let option = {

      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      },
    };
    return option;
  }


  index(): Observable<Address[]>{

    return this.http.get<Address[]>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("AddressService.index(): error retrieving list of Address" + err)
        )
      })
    )
  };


  create(address: Address): Observable<Address>{
    return this.http.post<Address>(this.url, address, this.getHttpOption()).pipe(
      catchError((err: any) =>{
        console.log(err);
        return throwError(
          () => new Error("AddressService.create(): error creating address" + err)
        )
      })
    );
  };


  show(id: number): Observable<Address>{
    return this.http.get<Address>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("AddressService.show(): error retrieving a address" + err)
        )
      })
    )
  };


  update(address: Address): Observable<Address>{
    return this.http.put<Address>(this.url+"/"+address.id, address, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("AddressService.update(): error updating address" + err)
        )
      })
    )
  };


  destroy(id: number): Observable<Address>{
    return this.http.delete<Address>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("addressService.destroy(): error destroy address" + err)
        )
      })
    )
  };


}
