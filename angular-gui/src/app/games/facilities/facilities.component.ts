import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Facility } from '../../entities/data_model';
import { FacilitiesService } from '../../services/facilities.service';

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css']
})
export class FacilitiesComponent implements OnInit {

  gameId: number;
  facilities: Facility[];
  expandAdd: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private facilitiesService: FacilitiesService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.getFacilities(this.gameId);
  }

  getFacilities(gameId: number): void {
    this.facilitiesService.getFacilities(gameId)
      .subscribe(facilities => this.facilities = facilities);
  }

  facilityAdded(facility: Facility) {
    if (facility != null) {
      this.facilities.push(facility);
    }
    this.expandAdd = false;
  }
}
