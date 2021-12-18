import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Standing } from 'src/app/models/sport-data-models/standing.model';
import { SportDataService } from 'src/app/services/sport-data/sport-data.service';

@Component({
  selector: 'app-nba-standing',
  templateUrl: './nba-standing.component.html',
  styleUrls: ['./nba-standing.component.css']
})
export class NbaStandingComponent implements OnInit {
  constructor(private route:ActivatedRoute, private sportDataService:SportDataService) {
  }
  leagueId:number = 0;
  standings:Standing[] = [];
  leagueName = "League";
  leagueLogoUrl = "";
  westernConference:Standing[] = [];
  easternConference:Standing[] = [];


  async ngOnInit(): Promise<void> {
    this.leagueId = this.route.snapshot.params['leagueId'];
    if (this.leagueId!=0){
      this.sportDataService.getSubscribedLeagues("BASKETBALL").subscribe(resp=>{
        resp.body?.forEach(league => {
          if (league.leagueId==this.leagueId){
            this.leagueName=league.name;
            this.leagueLogoUrl=league.logoUrl
          }
        }
      )})

      let tmpStandings = await this.sportDataService.getStandingsForLeague(this.leagueId,"BASKETBALL")
      this.standings= tmpStandings.body;
      this.standings.forEach(element=>{
        if (element.conference==="Western Conference"){
          this.westernConference.push(element);
        }else{
          this.easternConference.push(element);
        }
      })
    }
  }
}
