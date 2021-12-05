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
  getTeamsForLeague(id:number){
    return this.http.get<Team[]>(`${this.backendUrl}/leagues/`+id,{observe:'response'});
  }
}
