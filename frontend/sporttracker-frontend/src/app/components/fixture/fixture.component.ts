import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Fixture } from 'src/app/models/sport-data-models/fixtures.model';

@Component({
  selector: 'app-fixture',
  templateUrl: './fixture.component.html',
  styleUrls: ['./fixture.component.css']
})
export class FixtureComponent implements OnInit {

  constructor(private datePipe:DatePipe) { }

  @Input()
  data:Fixture={awayTeam:{},homeTeam:{}};

  ngOnInit(): void {
    if(this.data.event_timestamp){
      if(this.data.event_timestamp){
        let eventDate:Date = new Date(0);
        eventDate.setUTCSeconds(this.data.event_timestamp);
        this.data.eventDate = this.datePipe.transform(eventDate, "hh:mm dd-MM-yyyy")?.toString();
      }
    }
  }
  hasHometeamWon(){
    if (this.data.status == "Match Finished"){
      let homeGoals = this.data.goalsHomeTeam ? this.data.goalsHomeTeam : 0;
      let awayGoals = this.data.goalsAwayTeam ? this.data.goalsAwayTeam : 0;
      return homeGoals>awayGoals ? 1 : homeGoals<awayGoals ? -1 : 0;
    }
    return 0;
  }

  calculateFixtureColor(isHometown:boolean){
    if (this.data.status == "Match Finished" || this.data.status == "Game Finished"){
      let homeGoals = this.data.goalsHomeTeam ? this.data.goalsHomeTeam : 0;
      let awayGoals = this.data.goalsAwayTeam ? this.data.goalsAwayTeam : 0;
      if (homeGoals>awayGoals){return isHometown ? "winner" : "loser"}
      if (awayGoals>homeGoals){return isHometown ? "loser":"winner"}
      return "draw";
    }
    return "not-started"
  }
}
