export class Address {

  id: number;
  street: string;
  street2: string;
  city: string;
  state: string;
  zip: string;


  constructor(id: number, street: string, street2: string, city: string, state: string, zip: string){

    this.id = id;
    this.state = state;
    this.street2 = street2;
    this.street = street;
    this.city = city;
    this.zip = zip;

  }

}
