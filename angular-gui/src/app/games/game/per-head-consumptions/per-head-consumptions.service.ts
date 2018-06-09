import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { PerHeadConsumption } from './per-head-consumption';

@Injectable({
  providedIn: 'root'
})
export class PerHeadConsumptionsService {

  constructor(
    private http: HttpClient
  ) { }

  getConsumptions(gameId: number): Observable<PerHeadConsumption[]> {
    return this.http.get<PerHeadConsumption[]>('/api/games/' + gameId + '/perHeadConsumptions');
  }

  updateConsumptions(gameId: number, consumptions: PerHeadConsumption[]): Observable<PerHeadConsumption[]> {
    return this.http.put<PerHeadConsumption[]>(
      '/api/games/' + gameId + '/perHeadConsumptions',
      consumptions);
  }
}
