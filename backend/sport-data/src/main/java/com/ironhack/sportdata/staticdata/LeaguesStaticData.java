package com.ironhack.sportdata.staticdata;

import com.ironhack.sportdata.enums.Sport;
import com.ironhack.sportdata.models.League;

import java.util.Arrays;
import java.util.List;

public class LeaguesStaticData {
    private static final List<League> footballLeagues = Arrays.asList(
            new League(3510L,"https://media.api-sports.io/football/leagues/78.png","Bundesliga", Sport.FOOTBALL),
            new League(3576L,"https://media.api-sports.io/football/leagues/135.png", "Serie A", Sport.FOOTBALL),
            new League(3513L,"https://media.api-sports.io/football/leagues/140.png","Primera División", Sport.FOOTBALL),
            new League(3456L, "https://media.api-sports.io/football/leagues/39.png","Premier League", Sport.FOOTBALL),
            new League(3466L,"https://media.api-sports.io/football/leagues/106.png","Ekstraklasa", Sport.FOOTBALL),
            new League(3506L,"https://media.api-sports.io/football/leagues/61.png","Ligue 1", Sport.FOOTBALL),
            new League(3428L,"https://media.api-sports.io/football/leagues/119.png","Superligaen", Sport.FOOTBALL),
            new League(3321L,"https://media.api-sports.io/football/leagues/253.png","Major League Soccer", Sport.FOOTBALL),
            new League(3064L,"https://media.api-sports.io/football/leagues/648.png","Tasmania NPL", Sport.FOOTBALL)
    );
    private static  final List<League> basketballLeagues = Arrays.asList(
            new League(12L,"https://media.api-sports.io/basketball/leagues/12.png","NBA",Sport.BASKETBALL)
    );

    public static List<League> getFootballLeagues(){
        return footballLeagues;
    }
    public static List<League> getNBALeagues(){return basketballLeagues;}
}
