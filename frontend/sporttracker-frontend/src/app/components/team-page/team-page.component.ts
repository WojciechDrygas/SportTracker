import { Favorite } from './../../models/sport-data-models/favorite.model';
import { FavoritesService } from './../../services/favorites/favorites.service';
import { Fixture } from './../../models/sport-data-models/fixtures.model';
import { Router, ActivatedRoute } from '@angular/router';
import { SportDataService } from './../../services/sport-data/sport-data.service';
import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/sport-data-models/team.model';

@Component({
  selector: 'app-team-page',
  templateUrl: './team-page.component.html',
  styleUrls: ['./team-page.component.css']
})
export class TeamPageComponent implements OnInit {

  constructor(private sportDataService:SportDataService, private route:ActivatedRoute, private favoritesService:FavoritesService, private router:Router) { }

  teamId:number=0;
  leagueId:number=0;
  teamData:Team={};
  nextFixtures:Fixture[] = [];
  lastFixtures:Fixture[] = [];
  isFavorite:boolean = false;

  ngOnInit(): void {
    this.teamId = this.route.snapshot.params['teamId'];
    this.leagueId = this.route.snapshot.params['leagueId'];
    this.sportDataService.getTeamDataForId(this.teamId).subscribe(resp=>{
      if (resp.body){
        this.teamData=resp.body;
      }
    })
    this.sportDataService.getLastFixturesForTeam(this.teamId).subscribe(resp=>{
      if (resp.body){
        this.lastFixtures=resp.body;
      }
    })
    this.sportDataService.getNextFixturesForTeam(this.teamId).subscribe(resp=>{
      if (resp.body){
        this.nextFixtures=resp.body;
      }
    })
    this.favoritesService.getFavoritesForId().subscribe(resp=>{
      let tmpFavorite = false;
      resp.body?.forEach(favorite =>{
        if (favorite.teamId==this.teamId){
          this.isFavorite=true;
        }
      })
    })
  }
  onBack(){
    this.router.navigate(["/league/",this.leagueId]);
  }

  onDelete(){
    this.favoritesService.deleteFavoriteById(this.teamId).subscribe(resp=>{
      if (resp.status==204){
        location.reload()
      }  else{
        console.log("Error deleting favorite",resp.status)
      }
    })

  }
  onAdd(){
    let fav:Favorite={
      teamName:this.teamData.name,
      teamId:this.teamId,
      sport:"FOOTBALL",
      teamLogo:this.teamData.logo
    }
    this.favoritesService.postFavorite(fav).subscribe(resp=>{
      if (resp.status==202){
        location.reload();
      }
      else{
        console.log("Error adding team!",resp.status)
      }
    });
  }

}
