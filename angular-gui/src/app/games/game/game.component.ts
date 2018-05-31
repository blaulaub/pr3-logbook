import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Game } from '../entities/game';
import { GamesService } from '../../services/games.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  gameId: number;
  game: Game;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private gamesService: GamesService
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.gamesService.getGame(this.gameId)
      .subscribe(game => this.game = game);
  }

  updateGameDate(date: Date) {
    this.game.gameDate = date;
    this.gamesService.updateGame(this.game)
      .subscribe(game => this.game = game);
  }

}
