import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Facility } from '../entities/data_model';

@Injectable({
  providedIn: 'root'
})
export class FacilitiesService {

  constructor(
    private http: HttpClient
  ) { }

  getFacilities(gameId: number): Observable<Facility[]> {
    return this.http.get<Facility[]>('/api/games/' + gameId + '/facilities');
  }

  addFacility(gameId: number, name: string): Observable<Facility> {
    console.log("adding facility " + name);
    return this.http.post<Facility>('/api/games/' + gameId + '/facilities', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
