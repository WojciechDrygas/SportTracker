package com.ironhack.sportdata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fixture {
    private Long event_timestamp;
    private String round;
    private String status;
    private String venue;
    private FixtureTeam homeTeam;
    private FixtureTeam awayTeam;
    private Long goalsHomeTeam;
    private Long goalsAwayTeam;
}
