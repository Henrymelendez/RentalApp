import { end } from "@popperjs/core";

export class Lease {

  id: number;
  startDate: Date;
  endDate: Date;
  description: string;
  rentalAmount: number;
  securityDeposit: number;
  petDeposit: number;


  constructor(id: number, startDate: Date, endDate: Date, description: string, rentalAmount: number, securityDeposit: number, petDeposit: number){
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.description = description;
    this.rentalAmount = rentalAmount;
    this.securityDeposit = securityDeposit;
    this.petDeposit = petDeposit;
  }

}
