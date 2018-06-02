import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Game } from '../games/game';

@Injectable({
  providedIn: 'root'
})
export class GamesService {

  constructor(
    private http: HttpClient
  ) { }

  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>('/api/games');
  }

  getGame(gameId: number): Observable<Game> {
    return this.http.get<Game>('/api/games/' + gameId);
  }

  addGame(captainsName: string): Observable<Game> {
    console.log("adding captain " + captainsName);
    return this.http.post<Game>('/api/games', null, {
      params: new HttpParams().set('captainsName', captainsName)
    });
  }

  updateGame(game: Game): Observable<Game> {
    return this.http.put<Game>(
      '/api/games/' + game.id,
      game
    )
  }
}
