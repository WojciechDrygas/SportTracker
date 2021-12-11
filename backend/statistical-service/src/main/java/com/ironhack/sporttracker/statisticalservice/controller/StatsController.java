package com.ironhack.sporttracker.statisticalservice.controller;

import com.ironhack.sporttracker.statisticalservice.model.favorite.FavoriteTeamStatsDTO;
import com.ironhack.sporttracker.statisticalservice.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    private final StatsService statsService;

    @GetMapping("/stats/football/most-fav")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteTeamStatsDTO> getMostFav(){
        return statsService.getMostFav();
    }
}
