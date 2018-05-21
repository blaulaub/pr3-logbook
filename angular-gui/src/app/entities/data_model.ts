export class Game {
  constructor(
    public id: number,
    public captainsName: string,
    public created: Date
  ) {}
}

export class City {
  constructor(
    public id: number,
    public name: string
  ) {}
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
    public name: string,
    public cargoSpace?: number,
    public maneuverability?: number,
    public draft?: number,
    public minSpeed?: number,
    public maxSpeed?: number,
    public cannons?: number,
    public sailors?: number,
    public hitPoints?: number,
    public dailyCost?: number,
    public price?: number
  ) {}
}
