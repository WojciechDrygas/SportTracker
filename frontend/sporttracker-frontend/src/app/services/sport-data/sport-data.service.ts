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


  getSubscribedLeagues(sport:String){
    return this.http.get<League[]>(`${this.backendUrl}/leagues/`+sport,{observe:'response'});
  }
  getTeamDataForId(id:number, sport:string){
    return this.http.get<Team>(`${this.backendUrl}/teams/`+sport+`/`+id,{observe:'response'});
  }
  getLastFixturesForTeam(id:number,sport:string){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/fixtures/`+sport.toUpperCase()+`/last/`+id,{observe:'response'});
  }
  getNextFixturesForTeam(id:number,sport:string){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/fixtures/`+sport.toUpperCase()+`/next/`+id,{observe:'response'});
  }
  getNextFixturesForFavorites(favoritesId:number[],favoritesSport:string[]){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/favorites/fixtures/next/`+favoritesId.toString()+`/`+favoritesSport.toString(),{observe:'response'});
  }
  getLastFixturesForFavorites(favoritesId:number[],favoritesSport:string[]){
    return this.http.get<Fixture[]>(`${this.backendUrl}/teams/favorites/fixtures/last/`+favoritesId.toString()+`/`+favoritesSport.toString(),{observe:'response'});
  }
  async getStandingsForLeague(id:number,sport:string):Promise<any>{
    return this.http.get(`${this.backendUrl}/leagues/standings/`+sport+`/`+id,{observe:'response'}).toPromise();
  }
}
