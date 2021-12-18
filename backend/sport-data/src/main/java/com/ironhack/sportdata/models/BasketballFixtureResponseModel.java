package com.ironhack.sportdata.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketballFixtureResponseModel {
    @JsonProperty("timestamp")
    private Long event_timestamp;
    private String status;
    private FixtureTeam homeTeam;
    private FixtureTeam awayTeam;
    private Long goalsHomeTeam;
    private Long goalsAwayTeam;

    @JsonProperty("status")
    private void destructStatusObject(Map<String,String> status){
        this.status = status.get("long");
    }
    @JsonProperty("teams")
    private void destructTeamsObject(Map<String,Object> teams){
        try{
            Map<String,Object> home = (Map<String, Object>) teams.get("home");
            FixtureTeam homeTeamFromMap = new FixtureTeam();
            homeTeamFromMap.setTeam_name(home.get("name").toString());
            homeTeamFromMap.setLogo(home.get("logo").toString());

            Map<String,Object> away = (Map<String, Object>) teams.get("away");
            FixtureTeam awayTeamFromMap = new FixtureTeam();
            awayTeamFromMap.setTeam_name(away.get("name").toString());
            awayTeamFromMap.setLogo(away.get("logo").toString());

            homeTeam = homeTeamFromMap;
            awayTeam = awayTeamFromMap;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @JsonProperty("scores")
    private void destructScoresObject(Map<String,Object> scores){
        try{
            Map<String,Object> home = (Map<String, Object>) scores.get("home");
            goalsHomeTeam = Long.valueOf((Integer)home.get("total"));

            Map<String,Object> away = (Map<String, Object>) scores.get("away");
            goalsAwayTeam = Long.valueOf((Integer)away.get("total"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
