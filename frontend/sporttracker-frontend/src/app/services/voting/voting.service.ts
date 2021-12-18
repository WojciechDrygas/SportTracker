import { VoteResponse } from './../../models/sport-data-models/vote-response.model';
import { VotesForTeam } from './../../models/sport-data-models/votes-for-team.model';
import { Vote } from './../../models/sport-data-models/vote.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class VotingService {
  constructor(private http:HttpClient, private storageService:StorageService) { }

  private readonly backendUrl = "http://localhost:8000";

  private composeHeader(){
    return {
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+ this.storageService.getValue('jwt')
    }
  }

  putVote(vote:Vote){
    return this.http.put<VoteResponse>(`${this.backendUrl}/vote`,vote,{headers:this.composeHeader(), observe:'response'});
  }
  getVoteCount(teamId:number, sport:string){
    return this.http.get<VotesForTeam>(`${this.backendUrl}/votes/`+sport+`/`+teamId,{observe:'response'});
  }
  getCurrentVote(teamId:number, sport:string){
    return this.http.get<VoteResponse>(`${this.backendUrl}/vote/`+sport+`/`+teamId,{headers:this.composeHeader(), observe:'response'});
  }
}
