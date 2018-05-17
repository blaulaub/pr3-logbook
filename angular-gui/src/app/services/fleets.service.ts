import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Fleet } from '../entities/data_model';

@Injectable({
  providedIn: 'root'
})
export class FleetsService {

  constructor(
    private http: HttpClient
  ) { }

  getFleets(gameId: number): Observable<Fleet[]> {
    return this.http.get<Fleet[]>('/api/games/' + gameId + '/fleets');
  }

  addFleet(gameId: number, name: string): Observable<Fleet> {
    console.log("adding fleet " + name);
    return this.http.post<Fleet>('/api/games/' + gameId + '/fleets', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
