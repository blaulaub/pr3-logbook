import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { FactoryCount } from './factory-count';

@Injectable({
  providedIn: 'root'
})
export class CityFactoriesService {

  constructor(
    private http: HttpClient
  ) { }

  getFactoryCounts(gameId: number, cityId: number): Observable<FactoryCount[]> {
    return this.http.get<FactoryCount[]>(
      '/api/games/' + gameId + '/cities/' + cityId + '/factoryCounts');
  }

  updateFactoryCounts(gameId: number, cityId: number, counts: FactoryCount[]): Observable<FactoryCount[]> {
    return this.http.put<FactoryCount[]>(
      '/api/games/' + gameId + '/cities/' + cityId + '/factoryCounts',
      counts);
  }
}
