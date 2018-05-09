import { Component, OnInit } from '@angular/core';

import { Game } from '../entities/game';
import { GamesService } from '../services/games.service';

@Component({
  selector: 'app-game-add',
  templateUrl: './game-add.component.html',
  styleUrls: ['./game-add.component.css']
})
export class GameAddComponent implements OnInit {

  game: Game = new Game();

  constructor(private gamesService: GamesService) { }

  ngOnInit() {
  }

  onAdd(game :Game) {
    console.log("TODO: persist on server, maybe update local collection");
  }
}
