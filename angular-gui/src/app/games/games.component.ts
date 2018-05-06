import { Component, OnInit } from '@angular/core';
import { Game } from '../entities.game.ts';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  game: Game = {
    id: 1,
    captainsName: 'Morgan'
  };

  constructor() { }

  ngOnInit() {
  }

}
