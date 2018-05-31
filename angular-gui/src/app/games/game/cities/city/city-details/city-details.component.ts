import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { CityDetails } from './city-details';

@Component({
  selector: 'app-city-details',
  templateUrl: './city-details.component.html',
  styleUrls: ['./city-details.component.css']
})
export class CityDetailsComponent implements OnInit {

  @Input() gameId: number;
  @Input() cityId: number;

  editOn: boolean;
  cityDetails: CityDetails;

  cityDetailsForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    // TODO replace this mock code
    console.error("not implemented");
    this.cityDetails = {
      updated: new Date(),
      population: 1200,
      warehouses: 0
    }
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    this.cityDetailsForm = this.fb.group({
      population: this.cityDetails.population,
      warehouses: this.cityDetails.warehouses
    });
  }

  onSubmit() {
    const cityDetailsModel = this.cityDetailsForm.value;

    // TODO replace this mock code
    console.error("not implemented");
    this.cityDetails = {
      updated: new Date(),
      population: cityDetailsModel.population,
      warehouses: cityDetailsModel.warehouses
    }
    this.editOn = false;
  }

  cancelEdit() {
    this.editOn = false;
  }
}
