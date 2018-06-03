import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImportExportService {

  constructor(
    private http: HttpClient
  ) { }

  upload(gameId: number, savegame: String) {
    this.http
      .put(
        'api/games/' + gameId + '/raw',
        savegame,
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json'
          })
        })
      .subscribe(it => console.log('uploaded.'));
  }
}
