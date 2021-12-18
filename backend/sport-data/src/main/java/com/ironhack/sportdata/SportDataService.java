package com.ironhack.sportdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.sportdata.enums.FixtureOption;
import com.ironhack.sportdata.enums.Sport;
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

    public List<League> getFootballLeagues() {
        //Static data of leagues we will present, can be converted to external API call after the MVP
        return LeaguesStaticData.getFootballLeagues();
    }
    public List<League> getNBALeagues(){
        return LeaguesStaticData.getNBALeagues();
    }

//    @Cacheable(cacheNames = "TeamsForLeague")
//    public List<Team> getTeamsForLeague(Long id) throws JsonProcessingException {
//        return callAPI.getTeamsForLeague(id);
//    }
    @Cacheable(cacheNames = "TeamById")
    public Team getTeamById(Long id, Sport sport) throws JsonProcessingException {
        if (sport.equals(Sport.FOOTBALL)){
            return callAPI.getFootballTeamById(id);
        }else{
            return callAPI.getBasketballTeamById(id);
        }
    }
    @Cacheable(cacheNames = "FixturesTeamById")
    public List<Fixture> getFixturesTeamById(Long id, FixtureOption option, Sport sport) throws JsonProcessingException {
       if (sport.equals(Sport.FOOTBALL)){
            return callAPI.getFootballFixturesTeamById(id, option);
       }else{
           return callAPI.getBasketballFixturesTeamById(id,option);
       }
    }
    @Cacheable(cacheNames = "FixturesForFavorites")
    public List<Fixture> getFixturesForFavorites(List<Long> idList, FixtureOption option,List<String> sportList) throws JsonProcessingException {
        List<Fixture> favoriteFixtures = new ArrayList<>();
        int i = 0;
        for (Long id : idList) {
            i++;
            favoriteFixtures.addAll(this.getFixturesTeamById(id,option, Sport.valueOf(sportList.get(i))));
        }
        //sort them by date or other sorting
        return favoriteFixtures;
    }
    @Cacheable(cacheNames = "StandingsForLeague")
    public List<Standing> getStandingsForLeague(Long id, Sport sport) throws JsonProcessingException {
        if (sport.equals(Sport.FOOTBALL)){
            return callAPI.getStandingsForFootballLeagueById(id);
        }else{
            return callAPI.getStandingsForBasketballLeagueById(id);
        }

    }
}
