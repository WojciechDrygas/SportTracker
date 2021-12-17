package com.ironhack.sporttracker.statisticalservice.proxy;

import com.ironhack.sporttracker.statisticalservice.models.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SPORT-DATA-SERVICE")
public interface SportDataProxy {

    @GetMapping("/teams/{id}")
    Team getTeamById(@PathVariable Long id);
}
