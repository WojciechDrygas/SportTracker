package com.ironhack.sporttracker.favoriteservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteTeamStatsDTO {
    private Long teamId;
    private Long count;
}
