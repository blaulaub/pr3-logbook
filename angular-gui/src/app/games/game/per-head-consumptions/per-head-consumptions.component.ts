import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { PerHeadConsumption } from './per-head-consumption';
import { PerHeadConsumptionsService } from './per-head-consumptions.service';

@Component({
  selector: 'app-per-head-consumptions',
  templateUrl: './per-head-consumptions.component.html',
  styleUrls: ['./per-head-consumptions.component.css']
})
export class PerHeadConsumptionsComponent implements OnInit {

  @Input() gameId: number;

  editOn: boolean;
  consumptions: PerHeadConsumption[];

  consumptionsForm: FormGroup;

  constructor(
    private perHeadConsumptionsService: PerHeadConsumptionsService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.perHeadConsumptionsService
      .getConsumptions(this.gameId)
      .subscribe(consumptions => this.consumptions = consumptions);
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    this.consumptionsForm = this.fb.group({
      consumptions: this.fb.array( this.consumptions.map(x => this.fb.group({
        good: x.good,
        consumptionPerHundred: x.consumptionPerHundred
      })))
    });
  }

  // needed by html template
  get consumptionForms(): FormArray {
    return this.consumptionsForm.get('consumptions') as FormArray;
  }

  onSubmit() {
    const consumptionModel = this.consumptionsForm.value;
    const saveConsumptions = consumptionModel.consumptions.map(x => this.toPerHeadConsumption(x));
    this.perHeadConsumptionsService
      .updateConsumptions(this.gameId, saveConsumptions)
      .subscribe(consumptions => {
        this.consumptions = consumptions;
        this.editOn = false;
      });
  }

  cancelEdit() {
    this.editOn = false;
  }

  toPerHeadConsumption(x: any): PerHeadConsumption {
    return {
      good: x.good,
      consumptionPerHundred: x.consumptionPerHundred
    }
  }


}
