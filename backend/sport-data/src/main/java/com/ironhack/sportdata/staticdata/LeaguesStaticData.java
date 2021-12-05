package com.ironhack.sportdata.staticdata;

import com.ironhack.sportdata.leagues.models.League;

import java.util.Arrays;
import java.util.List;

public class LeaguesStaticData {
    private static final List<League> leagues = Arrays.asList(
            new League(78L,"https://media.api-sports.io/football/leagues/78.png","Bundesliga",3510L),
            new League(135L,"https://media.api-sports.io/football/leagues/135.png", "Serie A",3576L),
            new League(140L,"https://media.api-sports.io/football/leagues/140.png","Primera División",3513L),
            new League(39L, "https://media.api-sports.io/football/leagues/39.png","Premier League",3456L)
    );

    public static List<League> getLeagues(){
        return leagues;
    }
}
