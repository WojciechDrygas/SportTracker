package com.ironhack.sporttracker.favoriteservice.model;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long vote;
    private Long teamId;
    @Enumerated(EnumType.STRING)
    private Sport sport;

    public Vote(Long userId, Long vote, Long teamId, Sport sport) {
        this.userId = userId;
        this.vote = vote;
        this.teamId = teamId;
        this.sport = sport;
    }
}
