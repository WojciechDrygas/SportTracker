import { Fixture } from './../../models/sport-data-models/fixtures.model';
import { Team } from './../../models/sport-data-models/team.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { League } from 'src/app/models/sport-data-models/league.model';

@Injectable({
  providedIn: 'root'
})
export class SportDataService {

  constructor(private http:HttpClient) { }

  private readonly backendUrl = "http://localhost:8000";


  getSubscribedLeagues(){
    return this.http.get<League[]>(`${this.backendUrl}/leagues`,{observe:'response'});
  }
  async getTeamsForLeague(id:number):Promise<any>{
    return this.http.get(`${this.backendUrl}/leagues/`+id,{observe:'response'}).toPromise();
  }
  getTeamDataForId(id:number){
    return this.http.get<Team>(`${this.backendUrl}/teams/`+id,{observe:'response'});
  }
  getLastFixturesForTeam(id:number){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/fixtures/last/`+id,{observe:'response'});
  }
  getNextFixturesForTeam(id:number){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/fixtures/next/`+id,{observe:'response'});
  }
  getNextFixturesForFavorites(favoritesId:number[]){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/favorites/fixtures/next/`+favoritesId.toString(),{observe:'response'});
  }
  getLastFixturesForFavorites(favoritesId:number[]){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/favorites/fixtures/last/`+favoritesId.toString(),{observe:'response'});
  }
  async getStandingsForLeague(id:number):Promise<any>{
    return this.http.get(`${this.backendUrl}/leagues/standings/`+id,{observe:'response'}).toPromise();
  }
}
