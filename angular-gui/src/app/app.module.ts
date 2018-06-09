import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from '../app-routing.module';

import { AppComponent } from './app.component';
import { CitiesComponent } from './games/game/cities/cities.component';
import { CityComponent } from './games/game/cities/city/city.component';
import { CityAddComponent } from './games/game/cities/city-add/city-add.component';
import { CityDetailsComponent } from './games/game/cities/city/city-details/city-details.component';
import { CityProductsComponent } from './games/game/cities/city/city-products/city-products.component';
import { FacilitiesComponent } from './games/game/facilities/facilities.component';
import { FacilityAddComponent } from './games/game/facilities/facility-add/facility-add.component';
import { FacilityComponent } from './games/game/facilities/facility/facility.component';
import { FleetsComponent } from './games/game/fleets/fleets.component';
import { FleetComponent } from './games/game/fleets/fleet/fleet.component';
import { FleetAddComponent } from './games/game/fleets/fleet-add/fleet-add.component';
import { GamesComponent } from './games/games.component';
import { GameComponent } from './games/game/game.component';
import { GameDateComponent } from './games/game/game-date/game-date.component';
import { GameSettingsComponent } from './games/game/game-settings/game-settings.component';
import { GameAddComponent } from './games/game-add/game-add.component';
import { GoodsComponent } from './games/game/goods/goods.component';
import { GoodComponent } from './games/game/goods/good/good.component';
import { GoodAddComponent } from './games/game/goods/good-add/good-add.component';
import { ShiptypesComponent } from './games/game/shiptypes/shiptypes.component';
import { ShiptypeAddComponent } from './games/game/shiptypes/shiptype-add/shiptype-add.component';
import { ShiptypeComponent } from './games/game/shiptypes/shiptype/shiptype.component';
import { TurnoverEditComponent } from './games/game/facilities/facility/turnover-edit/turnover-edit.component';
import { CityFactoriesComponent } from './games/game/cities/city/city-factories/city-factories.component';
import { ImportExportComponent } from './games/game/import-export/import-export.component';
import { PerHeadConsumptionsComponent } from './games/game/per-head-consumptions/per-head-consumptions.component';

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
    FleetComponent,
    GameDateComponent,
    GameSettingsComponent,
    CityProductsComponent,
    CityDetailsComponent,
    CityFactoriesComponent,
    ImportExportComponent,
    PerHeadConsumptionsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
