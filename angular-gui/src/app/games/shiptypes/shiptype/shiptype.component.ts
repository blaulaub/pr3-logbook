import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Shiptype } from '../../../entities/data_model';
import { ShiptypesService } from '../../../services/shiptypes.service';

@Component({
  selector: 'app-shiptype',
  templateUrl: './shiptype.component.html',
  styleUrls: ['./shiptype.component.css']
})
export class ShiptypeComponent implements OnInit {

  gameId: number;
  shiptype: Shiptype;

  shiptypeForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private shiptypesService: ShiptypesService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.shiptypesService
      .getShiptype(this.gameId, +this.route.snapshot.paramMap.get('shiptypeId'))
      .subscribe(shiptype => {
        this.shiptype = shiptype;
        this.createForm();
      });
  }

  createForm() {
    this.shiptypeForm = this.fb.group({
      name: [ this.shiptype.name, Validators.required ],
      cargoSpace: this.shiptype.cargoSpace,
      maneuverability: this.shiptype.maneuverability,
      draft: this.shiptype.draft,
      minSpeed: this.shiptype.minSpeed,
      maxSpeed: this.shiptype.maxSpeed,
      cannons: this.shiptype.cannons,
      sailors: this.shiptype.sailors,
      hitPoints: this.shiptype.hitPoints,
      dailyCost: this.shiptype.dailyCost,
      price: this.shiptype.price
    });
  }

  onSubmit() {
    const shiptypeModel = this.shiptypeForm.value;
    const saveShiptype: Shiptype = {
      id: this.shiptype.id,
      name: shiptypeModel.name,
      cargoSpace: shiptypeModel.cargoSpace,
      maneuverability: shiptypeModel.maneuverability,
      draft: shiptypeModel.draft,
      minSpeed: shiptypeModel.minSpeed,
      maxSpeed: shiptypeModel.maxSpeed,
      cannons: shiptypeModel.cannons,
      sailors: shiptypeModel.sailors,
      hitPoints: shiptypeModel.hitPoints,
      dailyCost: shiptypeModel.dailyCost,
      price: shiptypeModel.price
    };
    this.shiptypesService
      .updateShiptype(this.gameId, saveShiptype)
      .subscribe(shiptype => {});
  }

}
