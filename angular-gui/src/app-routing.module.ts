import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GamesComponent } from './app/games/games.component';
import { GameComponent } from './app/games/game/game.component';
import { CitiesComponent } from './app/games/cities/cities.component';
import { FacilitiesComponent } from './app/games/facilities/facilities.component';
import { GoodsComponent } from './app/games/goods/goods.component';
import { ShipTypesComponent } from './app/games/ship-types/ship-types.component';
import { FleetsComponent } from './app/games/fleets/fleets.component';

const routes: Routes = [
  { path: '', redirectTo: '/games', pathMatch: 'full' },
  { path: 'games', component: GamesComponent },
  { path: 'games/:gameId', component: GameComponent }
  { path: 'games/:gameId/cities', component: CitiesComponent }
  { path: 'games/:gameId/facilities', component: FacilitiesComponent }
  { path: 'games/:gameId/goods', component: GoodsComponent }
  { path: 'games/:gameId/ship-types', component: ShipTypesComponent }
  { path: 'games/:gameId/fleets', component: FleetsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
