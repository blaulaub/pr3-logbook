import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { Good } from '../../../../../entities/data_model';
import { GoodsService } from '../../../../../services/goods.service';
import { CityProductsService } from '../../../../../services/city-products.service';

@Component({
  selector: 'app-city-products',
  templateUrl: './city-products.component.html',
  styleUrls: ['./city-products.component.css']
})
export class CityProductsComponent implements OnInit {

  @Input() gameId: number;
  @Input() cityId: number;

  editOn: boolean;
  products: Good[];
  goods: Good[];

  productsForm: FormGroup;

  constructor(
    private cityProductsService: CityProductsService,
    private goodsService: GoodsService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.cityProductsService.getProducts(this.gameId, this.cityId)
      .subscribe(products => {
        this.products = products;
        this.goodsService
          .getGoods(this.gameId)
          .subscribe(goods => this.goods = goods);
      })
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    var formData = {};
    for (let good of this.goods) {
      formData["good"+good.id] = this.products.find(it => it.name==good.name) != null;
    }
    this.productsForm = this.fb.group(formData);
  }

  onSubmit() {
    const formData = this.productsForm.value;
    const saveProducts: Good[] = [];
    for (let good of this.goods) {
      if (formData["good"+good.id]) {
        saveProducts.push(good);
      }
    }
    this.cityProductsService.updateProducts(this.gameId, this.cityId, saveProducts)
      .subscribe(products => {
        this.products = products;
        this.editOn = false;
      });
  }

  cancelEdit() {
    this.editOn = false;
  }
}
