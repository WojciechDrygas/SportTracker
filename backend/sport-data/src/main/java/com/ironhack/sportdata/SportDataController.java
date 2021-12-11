package com.ironhack.sportdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.sportdata.enums.FixtureOption;
import com.ironhack.sportdata.models.Fixture;
import com.ironhack.sportdata.models.League;
import com.ironhack.sportdata.models.Standing;
import com.ironhack.sportdata.models.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SportDataController {
    private final SportDataService sportDataService;

    public SportDataController(SportDataService sportDataService) {
        this.sportDataService = sportDataService;
    }

    @GetMapping("/leagues")
    public List<League> getLeagues() {
        return sportDataService.getLeagues();
    }

    @GetMapping("/leagues/{id}")
    public List<Team> getTeamsForLeague(@PathVariable Long id) throws JsonProcessingException {
        return sportDataService.getTeamsForLeague(id);
    }
    @GetMapping("/leagues/standings/{id}")
    public List<Standing> getStandingsForLeague(@PathVariable Long id) throws JsonProcessingException{
        return sportDataService.getStandingsForLeague(id);
    }
    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable Long id) throws JsonProcessingException {
        return sportDataService.getTeamById(id);
    }
    @GetMapping("/teams/fixtures/last/{id}")
    public List<Fixture> getLastFixturesTeamById(@PathVariable Long id) throws JsonProcessingException {
        return sportDataService.getFixturesTeamById(id, FixtureOption.LAST);
    }
    @GetMapping("/teams/fixtures/next/{id}")
    public List<Fixture> getNextFixturesTeamById(@PathVariable Long id) throws JsonProcessingException {
        return sportDataService.getFixturesTeamById(id, FixtureOption.NEXT);
    }
    @GetMapping("/teams/favorites/fixtures/next/{idList}")
    public List<Fixture> getNextFavoriteFixtures(@PathVariable List<Long> idList) throws JsonProcessingException {
        return sportDataService.getFixturesForFavorites(idList, FixtureOption.NEXT);
    }
    @GetMapping("/teams/favorites/fixtures/last/{idList}")
    public List<Fixture> getLastFavoriteFixtures(@PathVariable List<Long> idList) throws JsonProcessingException {
        return sportDataService.getFixturesForFavorites(idList, FixtureOption.LAST);
    }

}
