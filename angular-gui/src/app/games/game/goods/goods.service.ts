import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Good } from './good';
import { GoodBalance } from './good-balance';
import { City } from '../cities/city';

@Injectable({
  providedIn: 'root'
})
export class GoodsService {

  constructor(
    private http: HttpClient
  ) {}

  getGoods(gameId: number): Observable<Good[]> {
    return this.http.get<Good[]>('/api/games/' + gameId + '/goods');
  }

  getGoodBalances(gameId: number): Observable<GoodBalance[]> {
    return this.http.get<GoodBalance[]>('/api/games/' + gameId + '/goodBalances');
  }

  getGood(gameId: number, goodId: number): Observable<Good> {
    return this.http.get<Good>('/api/games/' + gameId + '/goods/' + goodId);
  }

  getProducedIn(gameId: number, goodId: number): Observable<City[]> {
    return this.http.get<City[]>('/api/games/' + gameId + '/goods/' + goodId + '/producingCities');
  }

  addGood(gameId: number, name: string): Observable<Good> {
    console.log("adding good " + name);
    return this.http.post<Good>('/api/games/' + gameId + '/goods', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
