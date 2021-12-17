package com.ironhack.sporttracker.favoriteservice.model;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotesForTeam {
    @Value(value = "0")
    private Long likes;
    @Value(value = "0")
    private Long dislikes;
    private Long teamId;
    private Sport sport;
}
