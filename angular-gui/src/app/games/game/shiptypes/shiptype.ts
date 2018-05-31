export class Shiptype {

  constructor(
    public id: number,
    public name: string
  ) {}

  cargoSpace: number;
  maneuverability: number;
  draft: number;
  minSpeed: number;
  maxSpeed: number;
  cannons: number;
  sailors: number;
  hitPoints: number;
  dailyCost: number;
  price: number;
}
