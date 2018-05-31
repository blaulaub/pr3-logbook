import { Component, Input, Output, EventEmitter  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { City } from '../city';
import { CitiesService } from '../../../../services/cities.service';

@Component({
  selector: 'app-city-add',
  templateUrl: './city-add.component.html',
  styleUrls: ['./city-add.component.css']
})
export class CityAddComponent {

  @Input() gameId: number;
  @Output() onCityAdded: EventEmitter<City> = new EventEmitter();

  cityForm: FormGroup;

  constructor(
    private citiesService: CitiesService,
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.cityForm = this.fb.group({
      name: [ null, Validators.required ]
    });
  }

  onSubmit() {
    this.citiesService.addCity(
      this.gameId,
      this.cityForm.get('name').value
    ).subscribe(city => this.onCityAdded.emit(city));
  }

  onCancel() {
      this.onCityAdded.emit(null);
    }

}
