import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { Facility } from '../facility';
import { Turnover } from './turnover';
import { Good } from '../../goods/good';
import { FacilitiesService } from '../../../../services/facilities.service';
import { GoodsService } from '../../../../services/goods.service';

@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrls: ['./facility.component.css']
})
export class FacilityComponent implements OnInit {

  editOn: boolean;
  gameId: number;
  facility: Facility;
  goods: Good[];

  facilityForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private facilitiesService: FacilitiesService,
    private goodsService: GoodsService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.facilitiesService
      .getFacility(this.gameId, +this.route.snapshot.paramMap.get('facilityId'))
      .subscribe(facility => {
        this.facility = facility;
        this.goodsService
          .getGoods(this.gameId)
          .subscribe(goods => this.goods = goods);
      });
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    this.facilityForm = this.fb.group({
      name: [ this.facility.name, Validators.required ],
      constructionCost: this.facility.constructionCost,
      constructionDays: this.facility.constructionDays,
      maintenancePerDay: this.facility.maintenancePerDay,
      workers: this.facility.workers,
      consumptions: this.fb.array( this.facility.consumption.map(x => this.fb.group(this.toTurnoverModel(x))) ),
      production: this.fb.group(this.toTurnoverModel(this.facility.production))
    });
  }

  get consumptions(): FormArray {
    return this.facilityForm.get('consumptions') as FormArray;
  }

  get production(): FormGroup {
    return this.facilityForm.get('production') as FormGroup;
  }

  toTurnoverModel(t : Turnover) {
    if (t != null) {
      return {
        good: t.good.name,
        amount: t.amount
      }
    } else {
      return {
        good: null,
        amount: null
      }
    }
  }

  addConsumption() {
    this.consumptions.push(this.fb.group(this.toTurnoverModel(null)));
  }

  clearConsumption(i: number) {
    this.consumptions.removeAt(i);
  }

  clearProduction() {
    this.production.setValue(this.toTurnoverModel(null));
    console.log("not implemented: clearProduction");
  }

  onSubmit() {
    const facilityModel = this.facilityForm.value;
    const saveFacility : Facility = {
      id: this.facility.id,
      name: facilityModel.name,
      constructionCost: facilityModel.constructionCost,
      constructionDays: facilityModel.constructionDays,
      maintenancePerDay: facilityModel.maintenancePerDay,
      workers: facilityModel.workers,
      consumption: facilityModel.consumptions.map(x => this.toTurnover(x)),
      production: this.toTurnover(facilityModel.production)
    };
    this.facilitiesService.updateFacility(this.gameId, saveFacility)
    .subscribe(facility => {
      this.facility = facility;
      this.editOn = false;
    });
  }

  cancelEdit() {
    this.editOn = false;
  }

  toTurnover(x : any) {
    if (x.amount == null || x.amount == 0) return null;
    if (x.good == null || x.good == '') return null;
    let goods = this.goods.filter(it => it.name == x.good);
    if (goods.length != 1) return null;
    return new Turnover(goods[0], x.amount);
  }

}
