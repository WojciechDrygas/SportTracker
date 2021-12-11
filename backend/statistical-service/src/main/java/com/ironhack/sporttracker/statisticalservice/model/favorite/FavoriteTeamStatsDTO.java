package com.ironhack.sporttracker.statisticalservice.model.favorite;

import com.ironhack.sporttracker.statisticalservice.enums.Sport;
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