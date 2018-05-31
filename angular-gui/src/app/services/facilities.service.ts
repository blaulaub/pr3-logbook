import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Facility } from '../games/game/facilities/facility';

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

  getFacility(gameId: number, facilityId: number): Observable<Facility> {
    return this.http.get<Facility>('/api/games/' + gameId + '/facilities/' + facilityId);
  }

  addFacility(gameId: number, name: string): Observable<Facility> {
    console.log("adding facility " + name);
    return this.http.post<Facility>('/api/games/' + gameId + '/facilities', null, {
      params: new HttpParams().set('name', name)
    });
  }

  updateFacility(gameId: number, facility: Facility): Observable<Facility> {
    return this.http.put<Facility>(
      '/api/games/' + gameId + '/facilities/' + facility.id,
      facility);
  }
}
