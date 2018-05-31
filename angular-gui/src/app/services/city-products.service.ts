import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Good } from '../games/game/goods/good';

@Injectable({
  providedIn: 'root'
})
export class CityProductsService {

  constructor(
    private http: HttpClient
  ) { }

  getProducts(gameId: number, cityId: number): Observable<Good[]> {
    return this.http.get<Good[]>('/api/games/' + gameId + '/cities/' + cityId + '/products');
  }

  updateProducts(gameId: number, cityId: number, products: Good[]): Observable<Good[]> {
    return this.http.put<Good[]>(
      '/api/games/' + gameId + '/cities/' + cityId + '/products',
      products);
  }
}
