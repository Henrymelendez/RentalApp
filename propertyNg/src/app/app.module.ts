import { TenantService } from './services/tenant.service';
import { MaintenanceService } from './services/maintenance.service';
import { AddressService } from './services/address.service';
import { ContractorService } from './services/contractor.service';
import { PaymentsService } from './services/payments.service';
import { PropertyTypeService } from './services/property-type.service';
import { UserService } from './services/user.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserModule,
    NgbModule


  ],
  providers: [
    AuthService,
    UserService,
    PropertyTypeService,
    PaymentsService,
    MaintenanceService,
    ContractorService,
    AddressService,
    TenantService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
