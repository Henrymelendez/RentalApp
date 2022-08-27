export class Tenant {
  id: number;
  registrationDate: Date;
  tenantName: string;
  phoneNumber: string;
  email: string;


  constructor(id: number, registrationDate: Date, tenentName: string, phoneNumber: string, email: string){

    this.id = id,
    this.registrationDate = registrationDate,
    this.tenantName = tenentName,
    this.phoneNumber = phoneNumber,
    this.email = email
  }

}
