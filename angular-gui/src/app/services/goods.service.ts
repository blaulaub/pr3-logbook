import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Good } from '../games/game/goods/good';

@Injectable({
  providedIn: 'root'
})
export class GoodsService {

  constructor(
    private http: HttpClient
  ) { }

  getGoods(gameId: number): Observable<Good[]> {
    return this.http.get<Good[]>('/api/games/' + gameId + '/goods');
  }

  getGood(gameId: number, goodId: number): Observable<Good> {
    return this.http.get<Good>('/api/games/' + gameId + '/goods/' + goodId);
  }

  addGood(gameId: number, name: string): Observable<Good> {
    console.log("adding good " + name);
    return this.http.post<Good>('/api/games/' + gameId + '/goods', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
