import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Good } from '../entities/data_model';

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

  addGood(gameId: number, name: string): Observable<Good> {
    console.log("adding good " + name);
    return this.http.post<Good>('/api/games/' + gameId + '/goods', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
