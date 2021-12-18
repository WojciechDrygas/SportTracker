package com.ironhack.sportdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.sportdata.enums.FixtureOption;
import com.ironhack.sportdata.enums.Sport;
import com.ironhack.sportdata.models.Fixture;
import com.ironhack.sportdata.models.League;
import com.ironhack.sportdata.models.Standing;
import com.ironhack.sportdata.models.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SportDataController {
    private final SportDataService sportDataService;

    public SportDataController(SportDataService sportDataService) {
        this.sportDataService = sportDataService;
    }

    @GetMapping("/leagues/{sport}")
    public List<League> getLeagues(@PathVariable Sport sport) {
        if (sport.equals(Sport.FOOTBALL)){
            return sportDataService.getFootballLeagues();
        }else{
            return sportDataService.getNBALeagues();
        }
    }

    @GetMapping("/leagues/standings/{sport}/{id}")
    public List<Standing> getStandingsForLeague(@PathVariable Long id, @PathVariable Sport sport) throws JsonProcessingException{
        return sportDataService.getStandingsForLeague(id, sport);
    }
    @GetMapping("/teams/{sport}/{id}")
    public Team getTeamById(@PathVariable Long id, @PathVariable Sport sport) throws JsonProcessingException {
        return sportDataService.getTeamById(id,sport);
    }
    @GetMapping("/teams/fixtures/{sport}/last/{id}")
    public List<Fixture> getLastFixturesTeamById(@PathVariable Long id,@PathVariable Sport sport) throws JsonProcessingException {
        return sportDataService.getFixturesTeamById(id, FixtureOption.LAST, sport);
    }
    @GetMapping("/teams/fixtures/{sport}/next/{id}")
    public List<Fixture> getNextFixturesTeamById(@PathVariable Long id,@PathVariable Sport sport) throws JsonProcessingException {
        return sportDataService.getFixturesTeamById(id, FixtureOption.NEXT,sport);
    }
    @GetMapping("/teams/favorites/fixtures/next/{idList}/{sportList}")
    public List<Fixture> getNextFavoriteFixtures(@PathVariable List<Long> idList,@PathVariable List<String> sportList) throws JsonProcessingException {
        return sportDataService.getFixturesForFavorites(idList, FixtureOption.NEXT,sportList);
    }
    @GetMapping("/teams/favorites/fixtures/last/{idList}/{sportList}")
    public List<Fixture> getLastFavoriteFixtures(@PathVariable List<Long> idList,@PathVariable List<String> sportList) throws JsonProcessingException {
        return sportDataService.getFixturesForFavorites(idList, FixtureOption.LAST,sportList);
    }

}
