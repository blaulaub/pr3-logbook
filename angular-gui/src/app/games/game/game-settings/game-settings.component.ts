import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { GameSettings } from './game-settings';
import { GameSettingsService } from '../../../services/game-settings.service';

@Component({
  selector: 'app-game-settings',
  templateUrl: './game-settings.component.html',
  styleUrls: ['./game-settings.component.css']
})
export class GameSettingsComponent implements OnInit {

  @Input() gameId: number;

  editOn: boolean;
  gameSettings: GameSettings;
  gameSettingsForm: FormGroup;

  constructor(
    private gameSettingsService: GameSettingsService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.gameSettingsService
      .getGameSettings(this.gameId)
      .subscribe(gameSettings => { this.gameSettings = gameSettings; });
  }

  switchToEdit() {
    this.createForm();
    this.editOn = true;
  }

  createForm() {
    this.gameSettingsForm = this.fb.group({
      salaryPerDay: this.gameSettings.salaryPerDay,
      workerPerCitizenRatio: this.gameSettings.workerPerCitizenRatio
    });
  }

  onSubmit() {
    const gameSettingsModel = this.gameSettingsForm.value;
    const saveGameSettings: GameSettings = {
      salaryPerDay: gameSettingsModel.salaryPerDay,
      workerPerCitizenRatio: gameSettingsModel.workerPerCitizenRatio
    };
    this.gameSettingsService
      .updateGameSettings(this.gameId, saveGameSettings)
      .subscribe(gameSettings => {
        this.gameSettings = gameSettings;
        this.editOn = false;
      });
  }

  cancelEdit() {
    this.editOn = false;
  }
}
