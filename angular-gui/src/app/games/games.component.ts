import { Component, OnInit } from '@angular/core';
import { Game } from '../entities/game';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  games: Game[] = [
    {
      id: 10,
      captainsName: 'Morgan',
      created: new Date("2018-05-06T17:09:14.682")
    },
    {
      id: 11,
      captainsName: 'Störtebecker',
      created: new Date("2018-05-06T17:19:00.000")
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
