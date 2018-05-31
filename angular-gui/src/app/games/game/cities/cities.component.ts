import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { City } from '../../../entities/data_model';
import { CitiesService } from '../../../services/cities.service';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {

  gameId: number;
  cities: City[];
  expandAdd: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private citiesService: CitiesService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.getCities(this.gameId);
  }

  getCities(gameId: number): void {
    this.citiesService.getCities(gameId)
      .subscribe(cities => this.cities = cities);
  }

  cityAdded(city: City) {
    if (city != null) {
      this.cities.push(city);
    }
    this.expandAdd = false;
  }
}
