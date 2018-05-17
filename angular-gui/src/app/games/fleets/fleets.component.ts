import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Fleet } from '../../entities/data_model';
import { FleetsService } from '../../services/fleets.service';

@Component({
  selector: 'app-fleets',
  templateUrl: './fleets.component.html',
  styleUrls: ['./fleets.component.css']
})
export class FleetsComponent implements OnInit {

  gameId: number;
  fleets: Fleet[];
  expandAdd: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private fleetsService: FleetsService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.getFleets(this.gameId);
  }

    getFleets(gameId: number): void {
      this.fleetsService.getFleets(gameId)
        .subscribe(fleets => this.fleets = fleets);
    }

    fleetAdded(fleet: Fleet) {
      if (fleet != null) {
        this.fleets.push(fleet);
      }
      this.expandAdd = false;
    }
}
