import { Good } from '../../goods/good';

export class Turnover {
  constructor(
    public good: Good,
    public amount: number
  ) {}
}
