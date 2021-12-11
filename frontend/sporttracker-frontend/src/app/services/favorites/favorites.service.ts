import { StorageService } from './../storage/storage.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Favorite } from 'src/app/models/sport-data-models/favorite.model';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {

  constructor(private http:HttpClient, private storageService:StorageService) { }

  private readonly backendUrl = "http://localhost:8000";

  private composeHeader(){
    return {
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+ this.storageService.getValue('jwt')
    }
  }

  postFavorite(favorite:Favorite){
    return this.http.post(`${this.backendUrl}/favorite_team`,favorite,{headers:this.composeHeader(), observe:'response'});
  }
  getFavoritesForId(){
    return this.http.get<Favorite[]>(`${this.backendUrl}/favorite_team/`,{headers:this.composeHeader(),observe:'response'});
  }
  deleteFavoriteById(id:number){
    return this.http.delete(`${this.backendUrl}/delete/favorite/football/`+id,{headers:this.composeHeader(),observe:'response'});
  }
}
