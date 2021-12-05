package com.ironhack.sportdata.leagues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.sportdata.external.CallAPI;
import com.ironhack.sportdata.leagues.models.League;
import com.ironhack.sportdata.leagues.models.Team;
import com.ironhack.sportdata.staticdata.LeaguesStaticData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {
    private final CallAPI callAPI;

    public LeagueService(CallAPI callAPI) {
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

    public List<Team> getTeamsForLeague(Long id) throws JsonProcessingException {
        return callAPI.getTeamsForLeague(id);
    }
}
