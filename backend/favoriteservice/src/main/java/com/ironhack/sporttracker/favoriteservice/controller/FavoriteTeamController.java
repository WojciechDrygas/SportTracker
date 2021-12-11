package com.ironhack.sporttracker.favoriteservice.controller;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.FavoriteTeamDTO;
import com.ironhack.sporttracker.favoriteservice.service.FavoriteTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteTeamController {

    private final FavoriteTeamService favoriteTeamService;

    public FavoriteTeamController(FavoriteTeamService favoriteTeamService) {
        this.favoriteTeamService = favoriteTeamService;
    }

    @PostMapping("/favorite_team")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FavoriteTeamDTO postFavoriteTeam(@RequestBody FavoriteTeamDTO favoriteTeamDTO, @RequestHeader (name="Authorization") String token){
        return favoriteTeamService.postFavoriteTeam(favoriteTeamDTO, token);
    }

    @GetMapping("/favorite_team")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteTeamDTO> getFavoritesForId(@RequestHeader (name="Authorization") String token){
        return favoriteTeamService.getFavoritesForId(token);
    }
    @DeleteMapping("/delete/favorite/football/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavorite(@PathVariable Long id, @RequestHeader (name="Authorization") String token){
        favoriteTeamService.deleteFavorite(id, token, Sport.FOOTBALL);
    }
}
