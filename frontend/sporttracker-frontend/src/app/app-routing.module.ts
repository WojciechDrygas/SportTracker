import { TeamPageComponent } from './components/team-page/team-page.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { LeagueComponent } from './components/league/league.component';
import { LeaguesComponent } from './components/leagues/leagues.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {
      path:'',
      component: HomeComponent
    },
    {
      path:'login',
      component: LoginComponent
    },
    {
      path:'register',
      component: RegisterComponent
    },
    {
      path:'leagues',
      component: LeaguesComponent
    },{
      path:'league/:leagueId',
      component: LeagueComponent
    },{
      path:'favorites',
      component: FavoritesComponent
    },{
      path:'teams/:leagueId/:teamId',
      component:TeamPageComponent
    }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
