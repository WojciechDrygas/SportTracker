package com.ironhack.sporttracker.favoriteservice.repositories;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findByUserIdAndTeamIdAndSport(Long userId, Long teamId, Sport sport);

    @Query(value = "SELECT Count(*) FROM votes WHERE team_id = :teamId AND sport = :sport AND vote=:vote GROUP BY team_id, sport",nativeQuery = true)
    Long countVotesForTeam(Long teamId, String sport, Long vote);

    @Query(value = "SELECT team_id, SUM(vote) as likes_sum, league_id, sport FROM votes WHERE vote = 1 GROUP BY team_id, league_id, sport ORDER BY likes_sum DESC LIMIT 5", nativeQuery = true)
    List<Object[]> getMostLiked();

    @Query(value = "SELECT team_id, SUM(vote) as dislikes_sum, league_id, sport FROM votes WHERE vote = -1 GROUP BY team_id, league_id, sport ORDER BY dislikes_sum LIMIT 5", nativeQuery = true)
    List<Object[]> getMostDisliked();

}
