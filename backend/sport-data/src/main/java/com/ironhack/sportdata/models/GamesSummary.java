package com.ironhack.sportdata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GamesSummary {
     private Long matchsPlayed;
     private Long win;
     private Long draw;
     private Long lose;
     private Long goalsFor;
     private Long goalsAgainst;
}
