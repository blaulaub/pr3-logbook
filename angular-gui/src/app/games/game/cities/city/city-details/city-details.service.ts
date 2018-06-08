import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CityDetails } from './city-details';

@Injectable({
  providedIn: 'root'
})
export class CityDetailsService {

  constructor(
    private http: HttpClient
  ) { }

  getCityDetails(gameId: number, cityId: number): Observable<CityDetails> {
    return this.http.get<CityDetails>(
      '/api/games/' + gameId + '/cities/' + cityId + '/cityDetails');
  }

  updateCityDetails(gameId: number, cityId: number, details: CityDetails): Observable<CityDetails> {
    return this.http.put<CityDetails>(
      '/api/games/' + gameId + '/cities/' + cityId + '/cityDetails',
      details);
  }
}
