export class Property {

  id: number;
  purchaseDate: Date;
  purchaseAmount: Date;
  notes: string;


  constructor(id: number, purchaseDate: Date, purchaseAmount: Date, note: string){

    this.id = id;
    this.purchaseDate = purchaseDate;
    this.purchaseAmount = purchaseAmount;
    this.notes = note
  }

}
