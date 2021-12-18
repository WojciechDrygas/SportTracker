import { Standing } from './../../models/sport-data-models/standing.model';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-conference-standings',
  templateUrl: './conference-standings.component.html',
  styleUrls: ['./conference-standings.component.css']
})
export class ConferenceStandingsComponent implements OnChanges {

  constructor() { }

  @Input()
  data:Standing[]=[];
  @Input()
  leagueId:number=0;
  @Input()
  conferenceName=""

  conference:Standing[]=[]
  ngOnChanges(changes: SimpleChanges): void {
    this.conference=this.data;
  }
  calculateRatio(index:number){
    let wins=this.conference[index].all.win!==undefined?this.conference[index].all.win:0;
    let played=this.conference[index].all.matchsPlayed!==undefined?this.conference[index].all.matchsPlayed:0;
    if (wins!==undefined&&played!==undefined){
      return (Math.round((wins/played)*100)/100).toFixed(2);
    }
    return 0;


  }
}
