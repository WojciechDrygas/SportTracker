package com.ironhack.sporttracker.favoriteservice.repositories;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findByUserIdAndTeamIdAndSport(Long userId, Long teamId, Sport sport);

    @Query(value = "SELECT Count(*) FROM votes WHERE team_id = :teamId AND sport = :sport AND vote=:vote GROUP BY team_id, sport",nativeQuery = true)
    Long countVotesForTeam(Long teamId, String sport, Long vote);
}
