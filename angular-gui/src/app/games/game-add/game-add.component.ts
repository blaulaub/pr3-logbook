import { Component, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Game } from '../game';
import { GamesService } from '../games.service';

@Component({
  selector: 'app-game-add',
  templateUrl: './game-add.component.html',
  styleUrls: ['./game-add.component.css']
})
export class GameAddComponent {

  @Output() onGameAdded: EventEmitter<Game> = new EventEmitter();

  gameForm: FormGroup;

  constructor(
    private gamesService: GamesService,
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.gameForm = this.fb.group({
      captainsName: [ null, Validators.required ]
    });
  }

  onSubmit() {
    this.gamesService.addGame(this.gameForm.get('captainsName').value).subscribe(game => this.onGameAdded.emit(game));
  }

  onCancel() {
    this.onGameAdded.emit(null);
  }

}
