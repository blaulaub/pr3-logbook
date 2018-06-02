import { Component, OnInit } from '@angular/core';

import { Game } from './game';
import { GamesService } from './games.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css'],
  providers: [ GamesService ]
})
export class GamesComponent implements OnInit {

  games: Game[];
  expandAdd: boolean;

  constructor(
    private gamesService: GamesService
  ) { }

  ngOnInit() {
    this.getGames();
  }

  getGames(): void {
    this.gamesService.getGames()
      .subscribe(games => this.games = games);
  }

  gameAdded(game: Game) {
    if (game != null) {
      this.games.push(game);
    }
    this.expandAdd = false;
  }
}
