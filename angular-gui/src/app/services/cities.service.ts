import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { City } from '../entities/data_model';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  constructor(
    private http: HttpClient
  ) { }

  getCities(gameId: number): Observable<City[]> {
    return this.http.get<City[]>('/api/games/' + gameId + '/cities');
  }

  addCity(gameId: number, name: string): Observable<City> {
    console.log("adding city " + name);
    return this.http.post<City>('/api/games/' + gameId + '/cities', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
