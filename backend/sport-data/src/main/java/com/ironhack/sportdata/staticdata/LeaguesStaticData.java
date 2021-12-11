package com.ironhack.sportdata.staticdata;

import com.ironhack.sportdata.models.League;

import java.util.Arrays;
import java.util.List;

public class LeaguesStaticData {
    private static final List<League> leagues = Arrays.asList(
            new League(3510L,"https://media.api-sports.io/football/leagues/78.png","Bundesliga"),
            new League(3576L,"https://media.api-sports.io/football/leagues/135.png", "Serie A"),
            new League(3513L,"https://media.api-sports.io/football/leagues/140.png","Primera División"),
            new League(3456L, "https://media.api-sports.io/football/leagues/39.png","Premier League"),
            new League(3466L,"https://media.api-sports.io/football/leagues/106.png","Ekstraklasa"),
            new League(3506L,"https://media.api-sports.io/football/leagues/61.png","Ligue 1"),
            new League(3428L,"https://media.api-sports.io/football/leagues/119.png","Superligaen"),
            new League(3321L,"https://media.api-sports.io/football/leagues/253.png","Major League Soccer"),
            new League(3064L,"https://media.api-sports.io/football/leagues/648.png","Tasmania NPL")
    );

    public static List<League> getLeagues(){
        return leagues;
    }
}
