package com.ironhack.sporttracker.favoriteservice.repositories;


import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.FavoriteTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {
    Optional<FavoriteTeam> findByOwnerIdAndTeamIdAndSport(Long ownerId, Long teamId, Sport sport);
    List<FavoriteTeam> findByOwnerId(Long ownerId);

    @Query(value = "SELECT * FROM favorite_team WHERE team_id IN (SELECT team_id FROM favorite_team WHERE sport = 'FOOTBALL' GROUP BY team_id ORDER BY count(*) DESC) LIMIT 5", nativeQuery = true)
    List<FavoriteTeam> getMostFav();
}
