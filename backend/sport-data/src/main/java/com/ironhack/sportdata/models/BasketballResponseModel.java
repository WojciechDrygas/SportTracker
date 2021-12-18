package com.ironhack.sportdata.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasketballResponseModel {
    @JsonProperty("position")
    private Long rank;
    private Long teamId;
    private String logoUrl;
    private String teamName;
    @JsonProperty("form")
    private String forme;
    private GamesSummary all;
    private String conference;

    @JsonProperty("team")
    private void destructTeamObject(Map<String, String> team){
        logoUrl = team.get("logo");
        teamName = team.get("name");
        teamId = new BigDecimal(team.get("id")).longValue();
    }
    @JsonProperty("games")
    private void destructGamesObject(Map<String,Object> games){
        try {
            GamesSummary allMap = new GamesSummary();
            allMap.setMatchsPlayed(new BigDecimal(games.get("played").toString()).longValue());
            allMap.setDraw(0L);
            Map<String, Object> win = (Map<String, Object>) games.get("win");
            allMap.setWin(Long.valueOf((Integer)win.get("total")));
            Map<String, Object> lose = (Map<String, Object>) games.get("lose");
            allMap.setLose(Long.valueOf((Integer)lose.get("total")));
            all=allMap;
        } catch (Exception ex){
            System.out.println("!!!Error destructing Games object");
            System.out.println(ex.getMessage());
            all=new GamesSummary(0L,0L, 0L, 0L, 0L, 0L);
        }
    }
    @JsonProperty("group")
    private void destructGroupObject(Map<String, String> group){
        conference = group.get("name");
    }
}
