package com.ironhack.sportdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.sportdata.enums.FixtureOption;
import com.ironhack.sportdata.external.CallAPI;
import com.ironhack.sportdata.models.Fixture;
import com.ironhack.sportdata.models.League;
import com.ironhack.sportdata.models.Standing;
import com.ironhack.sportdata.models.Team;
import com.ironhack.sportdata.staticdata.LeaguesStaticData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportDataService {
    private final CallAPI callAPI;

    public SportDataService(CallAPI callAPI) {
        this.callAPI = callAPI;
    }

//    @Cacheable(cacheNames = "SubscribedLeagues")
//    public List<League> getSubscribedLeagues() throws JsonProcessingException {
//        return callAPI.getSubscribedLeagues();
//    }

    public List<League> getLeagues() {
        //Static data of leagues we will present, can be converted to external API call after the MVP
        return LeaguesStaticData.getLeagues();
    }

    @Cacheable(cacheNames = "TeamsForLeague")
    public List<Team> getTeamsForLeague(Long id) throws JsonProcessingException {
        return callAPI.getTeamsForLeague(id);
    }
    @Cacheable(cacheNames = "TeamById")
    public Team getTeamById(Long id) throws JsonProcessingException {
        return callAPI.getTeamById(id);
    }
    @Cacheable(cacheNames = "FixturesTeamById")
    public List<Fixture> getFixturesTeamById(Long id, FixtureOption option) throws JsonProcessingException {
       return callAPI.getFixturesTeamById(id, option);
    }
    @Cacheable(cacheNames = "FixturesForFavorites")
    public List<Fixture> getFixturesForFavorites(List<Long> idList, FixtureOption option) throws JsonProcessingException {
        List<Fixture> favoriteFixtures = new ArrayList<>();
        for (Long id : idList) {
            favoriteFixtures.addAll(this.getFixturesTeamById(id,option));
        }
        //sort them by date or other sorting
        return favoriteFixtures;
    }
    @Cacheable(cacheNames = "StandingsForLeague")
    public List<Standing> getStandingsForLeague(Long id) throws JsonProcessingException {
        return callAPI.getStandingsForLeagueById(id);
    }
}
