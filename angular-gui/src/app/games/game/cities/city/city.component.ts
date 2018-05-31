import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { City } from '../city';
import { CitiesService } from '../../../../services/cities.service';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  gameId: number;
  city: City;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private citiesService: CitiesService,
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.citiesService
      .getCity(this.gameId, +this.route.snapshot.paramMap.get('cityId'))
      .subscribe(city => this.city = city);
  }

}
