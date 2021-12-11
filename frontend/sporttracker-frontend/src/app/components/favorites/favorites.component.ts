import { SportDataService } from './../../services/sport-data/sport-data.service';
import { FavoritesService } from './../../services/favorites/favorites.service';
import { Component, OnInit } from '@angular/core';
import { Favorite } from 'src/app/models/sport-data-models/favorite.model';
import { Fixture } from 'src/app/models/sport-data-models/fixtures.model';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  constructor(private favoritesService:FavoritesService, private sportDataService:SportDataService) { }

  favorites:Favorite[] = [];
  lastFixtures:Fixture[] = [];
  nextFixtures:Fixture[] = [];
  ngOnInit(): void {
    this.favoritesService.getFavoritesForId().subscribe(res =>{
      if (res.body){
        this.favorites = res.body;
        let favoritesId:number[] = [];
        this.favorites.forEach(fav => {
          if (fav.teamId){
            favoritesId.push(fav.teamId);
          }
        });
        if (favoritesId.length>0){
          this.sportDataService.getLastFixturesForFavorites(favoritesId).subscribe(resp=>{
            if (resp.body){
              this.lastFixtures=resp.body;
            }
          })
          this.sportDataService.getNextFixturesForFavorites(favoritesId).subscribe(resp=>{
            if (resp.body){
              this.nextFixtures=resp.body;
            }
          })
        }
      }
    })
  }
  onDelete(id?:number){
    if(id){
      this.favoritesService.deleteFavoriteById(id).subscribe(resp=>{
        if (resp.status==204){
          location.reload()
        }else{
          console.log("Error deleting team from favorites",resp.status);
        }

      })
    }

  }

}
