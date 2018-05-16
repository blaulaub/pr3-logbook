import { Component, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Game } from '../../entities/game';

@Component({
  selector: 'app-game-add',
  templateUrl: './game-add.component.html',
  styleUrls: ['./game-add.component.css']
})
export class GameAddComponent {

  @Output() onGameAdded: EventEmitter<Game> = new EventEmitter();

  gameForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.gameForm = this.fb.group({
      captainsName: [ '', Validators.required ]
    });
  }

  onSubmit() {
    // TODO persist
    console.log("not implemented: persist");
    this.onGameAdded.emit({
      captainsName: "WerAuchImmer",
      created: Date.now()
    }});
  }

}
