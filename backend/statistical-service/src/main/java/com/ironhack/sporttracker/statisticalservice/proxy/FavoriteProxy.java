package com.ironhack.sporttracker.statisticalservice.proxy;

import com.ironhack.sporttracker.statisticalservice.models.GenericStatsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("FAVORITE-SERVICE")
public interface FavoriteProxy {

    @GetMapping("/inner/stats/most-fav")
    List<GenericStatsDTO> getMostFav();

    @GetMapping("/inner/stats/likes")
    List<GenericStatsDTO> getMostLiked();

    @GetMapping("/inner/stats/dislikes")
    List<GenericStatsDTO> getMostDisliked();
}
