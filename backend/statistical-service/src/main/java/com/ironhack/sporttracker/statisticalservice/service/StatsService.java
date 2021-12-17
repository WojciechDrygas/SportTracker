package com.ironhack.sporttracker.statisticalservice.service;

import com.ironhack.sporttracker.statisticalservice.models.FavoriteTeamStatsDTO;
import com.ironhack.sporttracker.statisticalservice.models.Team;
import com.ironhack.sporttracker.statisticalservice.models.TeamStatsData;
import com.ironhack.sporttracker.statisticalservice.proxy.FavoriteProxy;
import com.ironhack.sporttracker.statisticalservice.proxy.SportDataProxy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    public StatsService(FavoriteProxy favoriteProxy, SportDataProxy sportDataProxy) {
        this.favoriteProxy = favoriteProxy;
        this.sportDataProxy = sportDataProxy;
    }

    private final FavoriteProxy favoriteProxy;
    private final SportDataProxy sportDataProxy;

    public List<TeamStatsData> getMostFav() {
        List<FavoriteTeamStatsDTO> favWithoutFullData = favoriteProxy.getMostFav();
        List<TeamStatsData> response = new ArrayList<>();
        for (FavoriteTeamStatsDTO fav:favWithoutFullData) {
            Team team = sportDataProxy.getTeamById(fav.getTeamId());
            TeamStatsData responseItem = new TeamStatsData();
            responseItem.setCount(fav.getCount());
            responseItem.setName(team.getName());
            responseItem.setLogoUrl(team.getLogoUrl());
            responseItem.setTeamId(fav.getTeamId());
            responseItem.setLeagueId(fav.getLeagueId());
            response.add(responseItem);
        }
        return response;
    }
}
