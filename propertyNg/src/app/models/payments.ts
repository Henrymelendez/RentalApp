export class Payments {
  id: number;
  amount: number;
  modeOfPayment: string;
  paymentNotes: string;


  constructor(id: number, amount: number, modeOfPayment: string, paymentNotes: string){
    this.id = id,
    this.amount = amount,
    this.modeOfPayment = modeOfPayment,
    this.paymentNotes = paymentNotes
  }

}
