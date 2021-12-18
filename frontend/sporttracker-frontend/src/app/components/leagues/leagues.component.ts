import { League } from '../../models/sport-data-models/league.model';
import { SportDataService } from '../../services/sport-data/sport-data.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-leagues',
  templateUrl: './leagues.component.html',
  styleUrls: ['./leagues.component.css']
})
export class LeaguesComponent implements OnInit {

  subscribedLeagues:League[]=[];

  constructor(private router:Router, private sportDataService:SportDataService) {
    this.sportDataService.getSubscribedLeagues("FOOTBALL").subscribe(
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
