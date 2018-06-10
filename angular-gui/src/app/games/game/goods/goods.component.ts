import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Good } from './good';
import { GoodBalance } from './good-balance';
import { GoodsService } from './goods.service';

@Component({
  selector: 'app-goods',
  templateUrl: './goods.component.html',
  styleUrls: ['./goods.component.css']
})
export class GoodsComponent implements OnInit {

  gameId: number;
  goods: Good[];
  goodBalances: GoodBalance[];
  expandAdd: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private goodsService: GoodsService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.getGoods(this.gameId);
  }

  getGoods(gameId: number): void {
    this.goodsService.getGoods(gameId)
      .subscribe(goods => this.goods = goods);
    this.goodsService.getGoodBalances(gameId)
      .subscribe(balance => this.goodBalances = balance);
  }

  getGlobalBalance(goodName: String) {
    const x = this.goodBalances.filter(it => it.good == goodName)[0];
    return Math.round(x.byRivalFactoryConsumption + x.byPlayerFactoryConsumption + x.byCityConsumption
    + x.byRivalFactoryProduction + x.byPlayerFactoryProduction);
  }

  getGlobalConsumption(goodName: String) {
    const x = this.goodBalances.filter(it => it.good == goodName)[0];
    return Math.round(x.byRivalFactoryConsumption + x.byPlayerFactoryConsumption + x.byCityConsumption);
  }

  getGlobalProduction(goodName: String) {
    const x = this.goodBalances.filter(it => it.good == goodName)[0];
    return Math.round(x.byRivalFactoryProduction + x.byPlayerFactoryProduction);
  }

  getRivalFactoryConsumption(goodName: String) {
    return Math.round(this.goodBalances.filter(it => it.good == goodName)[0].byRivalFactoryConsumption);
  }

  getRivalFactoryProduction(goodName: String) {
    return Math.round(this.goodBalances.filter(it => it.good == goodName)[0].byRivalFactoryProduction);
  }

  getPlayerFactoryConsumption(goodName: String) {
    return Math.round(this.goodBalances.filter(it => it.good == goodName)[0].byPlayerFactoryConsumption);
  }

  getPlayerFactoryProduction(goodName: String) {
    return Math.round(this.goodBalances.filter(it => it.good == goodName)[0].byPlayerFactoryProduction);
  }

  getCityConsumption(goodName: String) {
    return Math.round(this.goodBalances.filter(it => it.good == goodName)[0].byCityConsumption);
  }

  goodAdded(good: Good) {
    if (good != null) {
      this.goods.push(good);
    }
    this.expandAdd = false;
  }
}
