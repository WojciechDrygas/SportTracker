package com.ironhack.sportdata.leagues.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class League {
    private Long leagueId;
    private String logoUrl;
    private String name;
    private Long leagueIdV2;
}
