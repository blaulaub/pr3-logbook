import { Component, OnInit } from '@angular/core';

import { Game } from '../entities/game';
import { GamesService } from '../games.service';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  games: Game[];

  selectedGame: Game;

  constructor(private gamesService: GamesService) { }

  ngOnInit() {
    this.getGames();
  }

  getGames(): void {
    this.gamesService.getGames()
      .subscribe(games => this.games = games);
  }

  onSelect(game: Game): void {
    this.selectedGame = game;
  }
}
