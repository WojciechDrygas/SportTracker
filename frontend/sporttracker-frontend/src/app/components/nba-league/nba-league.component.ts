import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { League } from 'src/app/models/sport-data-models/league.model';
import { SportDataService } from 'src/app/services/sport-data/sport-data.service';

@Component({
  selector: 'app-nba-league',
  templateUrl: './nba-league.component.html',
  styleUrls: ['./nba-league.component.css']
})
export class NbaLeagueComponent implements OnInit {

  subscribedLeagues:League[]=[];

  constructor(private router:Router, private sportDataService:SportDataService) {
    this.sportDataService.getSubscribedLeagues("BASKETBALL").subscribe(
      res=>{
        if (res.body){
          this.subscribedLeagues = res.body;
        }
      }
    );
  }

  ngOnInit(): void {
  }

}
