import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';

import { CityDetails } from './city-details';
import { CityDetailsService } from './city-details.service';

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
    private cityDetailsService: CityDetailsService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.cityDetailsService.getCityDetails(this.gameId, this.cityId)
      .subscribe(details => this.cityDetails = details);
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    this.cityDetailsForm = this.fb.group({
      population: this.cityDetails.population,
      warehouses: this.cityDetails.warehouses,
      support: this.cityDetails.support,
      isExportCity: this.cityDetails.isExportCity
    });
  }

  onSubmit() {
    const cityDetailsModel = this.cityDetailsForm.value;
    const saveCityDetails : CityDetails = {
      population: cityDetailsModel.population,
      warehouses: cityDetailsModel.warehouses,
      support: cityDetailsModel.support,
      isExportCity: cityDetailsModel.isExportCity
    };
    this.cityDetailsService
      .updateCityDetails(this.gameId, this.cityId, saveCityDetails)
      .subscribe(details => {
        this.cityDetails = details;
        this.editOn = false;
      });
  }

  cancelEdit() {
    this.editOn = false;
  }
}
