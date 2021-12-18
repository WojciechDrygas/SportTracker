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
        let favoritesSport:string[]=[];
        this.favorites.forEach(fav => {
          if (fav.teamId&&fav.sport){
            favoritesId.push(fav.teamId);
            favoritesSport.push(fav.sport);
          }
        });
        if (favoritesId.length>0){
          this.sportDataService.getLastFixturesForFavorites(favoritesId,favoritesSport).subscribe(resp=>{
            if (resp.body){
              this.lastFixtures=resp.body;
            }
          })
          this.sportDataService.getNextFixturesForFavorites(favoritesId,favoritesSport).subscribe(resp=>{
            if (resp.body){
              this.nextFixtures=resp.body;
            }
          })
        }
      }
    })
  }
  onDelete(id?:number, sport?:string){
    if(id && sport){
      this.favoritesService.deleteFavoriteById(id, sport.toUpperCase()).subscribe(resp=>{
        if (resp.status==204){
          location.reload()
        }else{
          console.log("Error deleting team from favorites",resp.status);
        }

      })
    }

  }

}
