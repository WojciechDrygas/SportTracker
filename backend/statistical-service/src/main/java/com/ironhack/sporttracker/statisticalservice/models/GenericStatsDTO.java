package com.ironhack.sporttracker.statisticalservice.models;

import com.ironhack.sporttracker.statisticalservice.enums.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericStatsDTO {
    private Long teamId;
    private Long count;
    private Long leagueId;
    private Sport sport;
}