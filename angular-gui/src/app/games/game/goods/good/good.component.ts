import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Good } from '../good';
import { City } from '../../cities/city';
import { GoodsService } from '../goods.service';

@Component({
  selector: 'app-good',
  templateUrl: './good.component.html',
  styleUrls: ['./good.component.css']
})
export class GoodComponent implements OnInit {

  gameId: number;
  good: Good;
  producedIn: City[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private goodsService: GoodsService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.goodsService
      .getGood(this.gameId, +this.route.snapshot.paramMap.get('goodId'))
      .subscribe(good => {
        this.good = good;
        this.goodsService
          .getProducedIn(this.gameId, good.id)
             .subscribe(cities => {
               this.producedIn = cities;
             });
      });
  }

}
