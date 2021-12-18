package com.ironhack.sportdata.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Standing {
    private Long rank;
    @JsonProperty("team_id")
    private Long teamId;
    private String teamName;
    @JsonProperty("logo")
    private String logoUrl;
    private String forme;
    private GamesSummary all;
    private GamesSummary home;
    private GamesSummary away;
    private Long goalsDiff;
    private Long points;
    private String conference;
}
