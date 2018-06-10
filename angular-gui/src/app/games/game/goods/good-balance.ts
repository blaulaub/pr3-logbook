export class GoodBalance {
  constructor(
    public good: string,
    public byRivalFactoryConsumption: number,
    public byRivalFactoryProduction: number,
    public byPlayerFactoryProduction: number,
    public byPlayerFactoryConsumption: number,
    public byCityConsumption: number
  ) {}
}
