import { Turnover } from './facility/turnover';

export class Facility {

  constructor(
    public id: number,
    public name: string
  ) {}

  constructionCost: number;
  constructionDays: number;
  maintenancePerDay: number;
  workers: number;
  material: Turnover[];
  consumption: Turnover[];
  production: Turnover;
}
