import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { GamesService } from '../../services/games.service';

@Component({
  selector: 'app-game-add',
  templateUrl: './game-add.component.html',
  styleUrls: ['./game-add.component.css']
})
export class GameAddComponent implements OnInit {

  captainsName: string;

  constructor(
    private gamesService: GamesService
  ) { }

  ngOnInit() {
  }

  onSubmit() {
    this.gamesService.addGame(this.captainsName);
  }

  resetModel() {
    this.captainsName = '';
  }
}
