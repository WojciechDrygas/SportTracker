package com.ironhack.sporttracker.favoriteservice.repositories;


import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.FavoriteTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {
    Optional<FavoriteTeam> findByOwnerIdAndTeamIdAndSport(Long ownerId, Long teamId, Sport sport);
    List<FavoriteTeam> findByOwnerId(Long ownerId);
}
