import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Fleet } from '../fleet';
import { FleetsService } from '../../../../services/fleets.service';

@Component({
  selector: 'app-fleet',
  templateUrl: './fleet.component.html',
  styleUrls: ['./fleet.component.css']
})
export class FleetComponent implements OnInit {

  gameId: number;
  fleet: Fleet;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private fleetsService: FleetsService,
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.fleetsService
      .getFleet(this.gameId, +this.route.snapshot.paramMap.get('fleetId'))
      .subscribe(fleet => {
        this.fleet = fleet;
      });
  }

}
