import { Component, OnInit } from '@angular/core';

import { Game } from '../entities/data_model';
import { GamesService } from '../services/games.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
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
