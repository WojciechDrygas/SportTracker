package com.ironhack.sporttracker.statisticalservice.controller;

import com.ironhack.sporttracker.statisticalservice.models.TeamStatsData;
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
    public List<TeamStatsData> getMostFav(){
        return statsService.getMostFav();
    }
    @GetMapping("/stats/football/most-liked")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamStatsData> getMostLiked(){
        return statsService.getMostLiked();
    }
    @GetMapping("/stats/football/most-disliked")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamStatsData> getMostDisliked(){
        return statsService.getMostDisliked();
    }


}
