package com.ironhack.sportdata.leagues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.sportdata.leagues.models.League;
import com.ironhack.sportdata.leagues.models.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeagueController {
    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/leagues")
    public List<League> getLeagues() {
        return leagueService.getLeagues();
    }

    @GetMapping("/leagues/{id}")
    public List<Team> getTeamsForLeague(@PathVariable Long id) throws JsonProcessingException {
        return leagueService.getTeamsForLeague(id);
    }
}
