export class GameSettings {
  salaryPerDay: number;
  workerPerCitizenRatio: number;
}

export class City {
  constructor(
    public id: number,
    public name: string
  ) {}
}

export class CityDetails {
  constructor() {}

  updated: Date;
  population: number;
  warehouses: number;
}

export class Good {
  constructor(
    public id: number,
    public name: string
  ) {}
}

export class Facility {

  constructor(
    public id: number,
    public name: string
  ) {}

  constructionCost: number;
  constructionDays: number;
  maintenancePerDay: number;
  workers: number;
  consumption: Turnover[];
  production: Turnover;
}

export class Turnover {
  constructor(
    public good: Good,
    public amount: number
  ) {}
}

export class Fleet {
  constructor(
    public id: number,
    public name: string
  ) {}
}

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
