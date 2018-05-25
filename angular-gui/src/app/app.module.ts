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
import { ShiptypesComponent } from './games/shiptypes/shiptypes.component';
import { FleetsComponent } from './games/fleets/fleets.component';
import { GameComponent } from './games/game/game.component';
import { GameAddComponent } from './games/game-add/game-add.component';
import { CityAddComponent } from './games/cities/city-add/city-add.component';
import { GoodAddComponent } from './games/goods/good-add/good-add.component';
import { FacilityAddComponent } from './games/facilities/facility-add/facility-add.component';
import { FleetAddComponent } from './games/fleets/fleet-add/fleet-add.component';
import { ShiptypeAddComponent } from './games/shiptypes/shiptype-add/shiptype-add.component';
import { ShiptypeComponent } from './games/shiptypes/shiptype/shiptype.component';
import { FacilityComponent } from './games/facilities/facility/facility.component';
import { TurnoverEditComponent } from './games/facilities/facility/turnover-edit/turnover-edit.component';
import { CityComponent } from './games/cities/city/city.component';
import { GoodComponent } from './games/goods/good/good.component';
import { FleetComponent } from './games/fleets/fleet/fleet.component';


@NgModule({
  declarations: [
    AppComponent,
    GamesComponent,
    CitiesComponent,
    FacilitiesComponent,
    GoodsComponent,
    FleetsComponent,
    ShiptypesComponent,
    GameComponent,
    GameAddComponent,
    CityAddComponent,
    GoodAddComponent,
    FacilityAddComponent,
    FleetAddComponent,
    ShiptypeAddComponent,
    ShiptypeComponent,
    FacilityComponent,
    TurnoverEditComponent,
    CityComponent,
    GoodComponent,
    FleetComponent
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
