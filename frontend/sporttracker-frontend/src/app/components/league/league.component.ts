import { SportDataService } from './../../services/sport-data/sport-data.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Team } from 'src/app/models/sport-data-models/team.model';

@Component({
  selector: 'app-league',
  templateUrl: './league.component.html',
  styleUrls: ['./league.component.css']
})
export class LeagueComponent implements OnInit {
  constructor(private route:ActivatedRoute, private sportDataService:SportDataService) {
  }
  leagueId:number = 0;
  teams:Team[] = [];


  ngOnInit(): void {
    this.leagueId = this.route.snapshot.params['leagueId'];
    if (this.leagueId!=0){
      this.sportDataService.getTeamsForLeague(this.leagueId).subscribe(
        res=>{
          if (res.body){
            this.teams = res.body;
          }
        }
      );
    }
  }

}
