import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GamesComponent } from './app/games/games.component';
import { GameComponent } from './app/games/game/game.component';
import { CitiesComponent } from './app/games/game/cities/cities.component';
import { CityComponent } from './app/games/game/cities/city/city.component';
import { FacilitiesComponent } from './app/games/game/facilities/facilities.component';
import { FacilityComponent } from './app/games/game/facilities/facility/facility.component';
import { GoodsComponent } from './app/games/game/goods/goods.component';
import { GoodComponent } from './app/games/game/goods/good/good.component';
import { ShiptypesComponent } from './app/games/game/shiptypes/shiptypes.component';
import { ShiptypeComponent } from './app/games/game/shiptypes/shiptype/shiptype.component';
import { FleetsComponent } from './app/games/fleets/fleets.component';
import { FleetComponent } from './app/games/fleets/fleet/fleet.component';

const routes: Routes = [
  { path: '', redirectTo: '/games', pathMatch: 'full' },
  { path: 'games', component: GamesComponent },
  { path: 'games/:gameId', component: GameComponent },
  { path: 'games/:gameId/cities', component: CitiesComponent },
  { path: 'games/:gameId/cities/:cityId', component: CityComponent },
  { path: 'games/:gameId/facilities', component: FacilitiesComponent },
  { path: 'games/:gameId/facilities/:facilityId', component: FacilityComponent },
  { path: 'games/:gameId/goods', component: GoodsComponent },
  { path: 'games/:gameId/goods/:goodId', component: GoodComponent },
  { path: 'games/:gameId/shiptypes', component: ShiptypesComponent },
  { path: 'games/:gameId/shiptypes/:shiptypeId', component: ShiptypeComponent },
  { path: 'games/:gameId/fleets', component: FleetsComponent },
  { path: 'games/:gameId/fleets/:fleetId', component: FleetComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
