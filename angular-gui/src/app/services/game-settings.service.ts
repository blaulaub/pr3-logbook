import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameSettings } from '../games/game/game-settings/game-settings';

@Injectable({
  providedIn: 'root'
})
export class GameSettingsService {

  constructor(
    private http: HttpClient
  ) { }

  getGameSettings(gameId: number): Observable<GameSettings> {
    return this.http.get<GameSettings>('/api/games/' + gameId + '/gameSettings');
  }

  updateGameSettings(gameId: number, settings: GameSettings): Observable<GameSettings> {
    return this.http.put<GameSettings>(
      '/api/games/' + gameId + '/gameSettings',
      settings
    );
  }
}
