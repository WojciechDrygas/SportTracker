import { Router } from '@angular/router';
import { SportDataService } from './../../../services/sport-data/sport-data.service';
import { StatisticsService } from './../../../services/statistics/statistics.service';
import { TeamStat } from './../../../models/stats/team-stat.model';
import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/sport-data-models/team.model';

@Component({
  selector: 'app-most-favorite',
  templateUrl: './most-favorite.component.html',
  styleUrls: ['./most-favorite.component.css']
})
export class MostFavoriteComponent implements OnInit {

  constructor(private statisticsService:StatisticsService, private sportDateService:SportDataService, private router:Router) { }

  data:TeamStat[]=[]
  ngOnInit(): void {
  this.statisticsService.getMostFavorite().subscribe(resp=>{
    if (resp.body){
      this.data=resp.body;
    }});
  }

}
