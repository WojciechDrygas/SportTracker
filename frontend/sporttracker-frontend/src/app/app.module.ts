import { InterceptorService } from './services/interceptor/interceptor.service';
import { NgModule, Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LeaguesComponent } from './components/leagues/leagues.component';
import { LeagueComponent } from './components/league/league.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { TeamPageComponent } from './components/team-page/team-page.component';
import { FixtureComponent } from './components/fixture/fixture.component';
import { DatePipe } from '@angular/common';
import { MostFavoriteComponent } from './components/stats/most-favorite/most-favorite.component';
import { VotingComponent } from './components/voting/voting.component';
import { LikeStatsComponent } from './components/stats/like-stats/like-stats.component';
import { FixturesListComponent } from './components/fixtures-list/fixtures-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { NbaLeagueComponent } from './components/nba-league/nba-league.component';
import { NbaStandingComponent } from './components/nba-standing/nba-standing.component';
import { ConferenceStandingsComponent } from './components/conference-standings/conference-standings.component'
import { LowerCaseUrlSerializer } from './utils/LowerCaseUrlSerializer.model';
import { UrlSerializer } from '@angular/router';

@NgModule({

  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    HeaderComponent,
    LeaguesComponent,
    LeagueComponent,
    FavoritesComponent,
    TeamPageComponent,
    FixtureComponent,
    MostFavoriteComponent,
    VotingComponent,
    LikeStatsComponent,
    FixturesListComponent,
    NbaLeagueComponent,
    NbaStandingComponent,
    ConferenceStandingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatProgressBarModule
  ],
  providers: [DatePipe,{provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi:true},{
    provide: UrlSerializer,
    useClass: LowerCaseUrlSerializer
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
