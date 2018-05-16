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
