import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Shiptype } from './shiptype';
import { ShiptypesService } from './shiptypes.service';

@Component({
  selector: 'app-shiptypes',
  templateUrl: './shiptypes.component.html',
  styleUrls: ['./shiptypes.component.css']
})
export class ShiptypesComponent implements OnInit {

  gameId: number;
  shiptypes: Shiptype[];
  expandAdd: boolean;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private shiptypesService: ShiptypesService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.getShiptypes(this.gameId);
  }

    getShiptypes(gameId: number): void {
      this.shiptypesService.getShiptypes(gameId)
        .subscribe(shiptypes => this.shiptypes = shiptypes);
    }

    shiptypeAdded(shiptype: Shiptype) {
      if (shiptype != null) {
        this.shiptypes.push(shiptype);
      }
      this.expandAdd = false;
    }
}
