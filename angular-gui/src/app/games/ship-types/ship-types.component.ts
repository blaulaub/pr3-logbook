import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-ship-types',
  templateUrl: './ship-types.component.html',
  styleUrls: ['./ship-types.component.css']
})
export class ShipTypesComponent implements OnInit {

  gameId: number;

  constructor(
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
  }

}
