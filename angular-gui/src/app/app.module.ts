import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from '../app-routing.module';

import { AppComponent } from './app.component';
import { GamesComponent } from './games/games.component';
import { CitiesComponent } from './games/cities/cities.component';
import { FacilitiesComponent } from './games/facilities/facilities.component';
import { GoodsComponent } from './games/goods/goods.component';
import { ShipTypesComponent } from './games/ship-types/ship-types.component';
import { FleetsComponent } from './games/fleets/fleets.component';
import { GameComponent } from './games/game/game.component';


@NgModule({
  declarations: [
    AppComponent,
    GamesComponent,
    CitiesComponent,
    FacilitiesComponent,
    GoodsComponent,
    FleetsComponent,
    ShipTypesComponent,
    GameComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
