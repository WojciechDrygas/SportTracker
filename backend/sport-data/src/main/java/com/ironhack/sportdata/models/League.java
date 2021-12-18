package com.ironhack.sportdata.models;

import com.ironhack.sportdata.enums.Sport;
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
    private Sport sport;
}
