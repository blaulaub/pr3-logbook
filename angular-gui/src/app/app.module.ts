import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from '../app-routing.module';

import { AppComponent } from './app.component';
import { GamesComponent } from './games/games.component';
import { CitiesComponent } from './games/cities/cities.component';
import { FacilitiesComponent } from './games/facilities/facilities.component';
import { GoodsComponent } from './games/goods/goods.component';
import { ShipTypesComponent } from './games/ship-types/ship-types.component';
import { FleetsComponent } from './games/fleets/fleets.component';
import { GameComponent } from './games/game/game.component';
import { GameAddComponent } from './games/game-add/game-add.component';
import { CityAddComponent } from './games/cities/city-add/city-add.component';
import { GoodAddComponent } from './games/goods/good-add/good-add.component';


@NgModule({
  declarations: [
    AppComponent,
    GamesComponent,
    CitiesComponent,
    FacilitiesComponent,
    GoodsComponent,
    FleetsComponent,
    ShipTypesComponent,
    GameComponent,
    GameAddComponent,
    CityAddComponent,
    GoodAddComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
