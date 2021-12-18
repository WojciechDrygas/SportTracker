import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TeamStat } from 'src/app/models/stats/team-stat.model';
import { SportDataService } from 'src/app/services/sport-data/sport-data.service';
import { StatisticsService } from 'src/app/services/statistics/statistics.service';

@Component({
  selector: 'app-like-stats',
  templateUrl: './like-stats.component.html',
  styleUrls: ['./like-stats.component.css']
})
export class LikeStatsComponent implements OnInit {

  constructor(private statisticsService:StatisticsService, private sportDateService:SportDataService, private router:Router) { }

  @Input()
  isLiked=true;

  data:TeamStat[]=[]
  ngOnInit(): void {
    if(this.isLiked){
      this.statisticsService.getMostLiked().subscribe(resp=>{
        if (resp.body){
          this.data=resp.body;
        }});
  }else{
    this.statisticsService.getMostDisliked().subscribe(resp=>{
      if (resp.body){
        this.data=resp.body;
      }});
  }
}
}
