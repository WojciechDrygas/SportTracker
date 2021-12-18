package com.ironhack.sporttracker.favoriteservice.model;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;
    private Long teamId;
    private String teamName;
    private String teamLogo;

    @Enumerated(value = EnumType.STRING)
    private Sport sport;
    private Long leagueId;

    public FavoriteTeam(Long ownerId, Long teamId, String teamName, String teamLogo, Sport sport, Long leagueId) {
        this.ownerId = ownerId;
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.sport = sport;
        this.leagueId = leagueId;
    }
}
