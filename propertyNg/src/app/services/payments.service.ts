import { Payments } from './../models/payments';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {


  private url = environment.baseUrl +"api/payment"

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


  index(): Observable<Payments[]>{
    return this.http.get<Payments[]>(this.url, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("PaymentService.index(): Error returning a list of Payment" + err)
        )
      })
    );
  };

  create(payment: Payments): Observable<Payments> {
    return this.http.post<Payments>(this.url, payment, this.getHttpOption()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error("PaymentsService.create() error creating payment:" + err)
        )
      })
    );
  };

  show(id: number): Observable<Payments>{

    return this.http.get<Payments>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError ((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("PaymentsService.show(): error retrieving Payment" + err)
        )
      })
    )
  }

  update(payments: Payments): Observable<Payments>{

    return this.http.put<Payments>(this.url+"/"+payments.id, payments, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("PaymentsSevice.update(): error updating Payments"+ err)
        )
      })
    )
  };

  destroy(id: number): Observable<Payments>{
    return this.http.delete<Payments>(this.url+"/"+id, this.getHttpOption()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error("PaymentsService.destroy(): Error destroying payment"+ err)

        )

      })
    )
  };



}
