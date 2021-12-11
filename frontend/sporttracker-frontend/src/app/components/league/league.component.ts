import { League } from './../../models/sport-data-models/league.model';
import { Standing } from './../../models/sport-data-models/standing.model';
import { SportDataService } from './../../services/sport-data/sport-data.service';
import { Component, Input, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-league',
  templateUrl: './league.component.html',
  styleUrls: ['./league.component.css']
})
export class LeagueComponent implements OnInit {
  constructor(private route:ActivatedRoute, private sportDataService:SportDataService) {
  }
  leagueId:number = 0;
  standings:Standing[] = [];
  @Input()
  leagueName = "League";
  leagueLogoUrl = "";


  async ngOnInit(): Promise<void> {
    this.leagueId = this.route.snapshot.params['leagueId'];
    if (this.leagueId!=0){
      this.sportDataService.getSubscribedLeagues().subscribe(resp=>{
        resp.body?.forEach(league => {
          if (league.leagueId==this.leagueId){
            this.leagueName=league.name;
            this.leagueLogoUrl=league.logoUrl
          }
        }
      )})

      let tmpStandings = await this.sportDataService.getStandingsForLeague(this.leagueId)
      this.standings= tmpStandings.body;
      console.log("Standings object:",this.standings);
    }
  }
}
