import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Good } from '../../../entities/data_model';
import { GoodsService } from '../../../services/goods.service';

@Component({
  selector: 'app-goods',
  templateUrl: './goods.component.html',
  styleUrls: ['./goods.component.css']
})
export class GoodsComponent implements OnInit {

  gameId: number;
  goods: Good[];
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
    }

    goodAdded(good: Good) {
      if (good != null) {
        this.goods.push(good);
      }
      this.expandAdd = false;
    }
}
