import { TeamStat } from './../../models/stats/team-stat.model';
import { SportDataService } from './../sport-data/sport-data.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private http:HttpClient, private storageService:StorageService, private sportDataService:SportDataService) { }

  private readonly backendUrl = "http://localhost:8000";

  private composeHeader(){
    return {
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+ this.storageService.getValue('jwt')
    }
  }

  getMostFavorite(){
    return this.http.get<TeamStat[]>(`${this.backendUrl}/stats/football/most-fav`,{ observe:'response'})
  }
}
