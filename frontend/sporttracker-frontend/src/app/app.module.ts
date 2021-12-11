import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LeaguesComponent } from './components/leagues/leagues.component';
import { LeagueComponent } from './components/league/league.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { TeamPageComponent } from './components/team-page/team-page.component';
import { FixtureComponent } from './components/fixture/fixture.component';
import { DatePipe } from '@angular/common';

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
    FixtureComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
