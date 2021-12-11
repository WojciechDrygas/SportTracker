package com.ironhack.sporttracker.statisticalservice.service;

import com.ironhack.sporttracker.statisticalservice.model.favorite.FavoriteTeamStatsDTO;
import com.ironhack.sporttracker.statisticalservice.proxy.FavoriteProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    public StatsService(FavoriteProxy favoriteProxy) {
        this.favoriteProxy = favoriteProxy;
    }

    private final FavoriteProxy favoriteProxy;

    public List<FavoriteTeamStatsDTO> getMostFav() {
        return favoriteProxy.getMostFav();
    }
}
