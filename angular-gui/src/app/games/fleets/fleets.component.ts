import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-fleets',
  templateUrl: './fleets.component.html',
  styleUrls: ['./fleets.component.css']
})
export class FleetsComponent implements OnInit {

  gameId: number;

  constructor(
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
  }

}
