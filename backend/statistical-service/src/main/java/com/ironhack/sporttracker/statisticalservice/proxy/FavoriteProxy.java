package com.ironhack.sporttracker.statisticalservice.proxy;

import com.ironhack.sporttracker.statisticalservice.model.favorite.FavoriteTeamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("FAVORITE-SERVICE")
public interface FavoriteProxy {

    @GetMapping("/inner/stats/most-fav")
    List<FavoriteTeamDTO> getMostFav();
}
