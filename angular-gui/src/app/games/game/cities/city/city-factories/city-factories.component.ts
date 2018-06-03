import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { FactoryCount } from './factory-count';
import { CityFactoriesService } from './city-factories.service';

@Component({
  selector: 'app-city-factories',
  templateUrl: './city-factories.component.html',
  styleUrls: ['./city-factories.component.css']
})
export class CityFactoriesComponent implements OnInit {

  @Input() gameId: number;
  @Input() cityId: number;

  editOn: boolean;
  factoryCounts: FactoryCount[];

  factoryCountsForm: FormGroup;

  constructor(
    private cityFactoriesService: CityFactoriesService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.cityFactoriesService.getFactoryCounts(this.gameId, this.cityId)
      .subscribe(counts => this.factoryCounts = counts);
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    this.factoryCountsForm = this.fb.group({
      factories: this.fb.array( this.factoryCounts.map(x => this.fb.group({
        facilityId: x.facilityId,
        facilityName: x.facilityName,
        playerCount: x.playerCount,
        rivalCount: x.rivalCount
      })))
    });
  }

  // needed by html template
  get factories(): FormArray {
    return this.factoryCountsForm.get('factories') as FormArray;
  }

  onSubmit() {
    const countModel = this.factoryCountsForm.value;
    const saveFactoryCounts = countModel.factories.map(x => this.toFactoryCount(x));
    this.cityFactoriesService
      .updateFactoryCounts(this.gameId, this.cityId, saveFactoryCounts)
      .subscribe(counts => {
        this.factoryCounts = counts;
        this.editOn = false;
      });
  }

  cancelEdit() {
    this.editOn = false;
  }

  toFactoryCount(x: any): FactoryCount {
    return {
      facilityId: x.facilityId,
      facilityName: x.facilityName,
      playerCount: x.playerCount,
      rivalCount: x.rivalCount
    }
  }
}
