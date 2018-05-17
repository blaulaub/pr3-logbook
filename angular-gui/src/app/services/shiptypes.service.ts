import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Shiptype } from '../entities/data_model';

@Injectable({
  providedIn: 'root'
})
export class ShiptypesService {

  constructor(
    private http: HttpClient
  ) { }

  getShiptypes(gameId: number): Observable<Shiptype[]> {
    return this.http.get<Shiptype[]>('/api/games/' + gameId + '/shiptypes');
  }

  addShiptype(gameId: number, name: string): Observable<Shiptype> {
    console.log("adding shiptype " + name);
    return this.http.post<Shiptype>('/api/games/' + gameId + '/shiptypes', null, {
      params: new HttpParams().set('name', name)
    });
  }
}
