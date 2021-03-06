package com.ironhack.sporttracker.favoriteservice.service;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.FavoriteTeam;
import com.ironhack.sporttracker.favoriteservice.model.FavoriteTeamDTO;
import com.ironhack.sporttracker.favoriteservice.model.GenericStatsDTO;
import com.ironhack.sporttracker.favoriteservice.repositories.FavoriteTeamRepository;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteTeamService {

    private final FavoriteTeamRepository favoriteTeamRepository;
    private final Environment environment;
    private final ModelMapper modelMapper;

    public FavoriteTeamService(FavoriteTeamRepository favoriteTeamRepository, Environment environment, ModelMapper modelMapper) {
        this.favoriteTeamRepository = favoriteTeamRepository;
        this.environment = environment;
        this.modelMapper =modelMapper;
    }

    public FavoriteTeamDTO postFavoriteTeam(FavoriteTeamDTO favoriteTeamDTO, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());

        Optional<FavoriteTeam> matchingObject = favoriteTeamRepository.findByOwnerIdAndTeamIdAndSport(
                tokenSubject,
                favoriteTeamDTO.getTeamId(),
                favoriteTeamDTO.getSport());
        if (matchingObject.isPresent()) {
            return null;
        }else {
            FavoriteTeam objectToSave = modelMapper.map(favoriteTeamDTO,FavoriteTeam.class);
            objectToSave.setOwnerId(tokenSubject);
            FavoriteTeam savedObject = favoriteTeamRepository.save(objectToSave);
            return modelMapper.map(savedObject,FavoriteTeamDTO.class);
        }
    }


    public List<FavoriteTeamDTO> getFavoritesForId(String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());

        List<FavoriteTeam> entityList = favoriteTeamRepository.findByOwnerId(tokenSubject);
        List<FavoriteTeamDTO> response = new ArrayList<>();
        for (FavoriteTeam entity: entityList) {
            response.add(modelMapper.map(entity,FavoriteTeamDTO.class));
        }
        return response;
    }

    public void deleteFavorite(Long id, String token, Sport sport) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Optional<FavoriteTeam> favoriteToDelete = favoriteTeamRepository.findByOwnerIdAndTeamIdAndSport(tokenSubject,id,sport);
        if (favoriteToDelete.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User doesn't have this team in his favorites!");
        }else{
            favoriteTeamRepository.delete(favoriteToDelete.get());
        }

    }

    public List<GenericStatsDTO> getMostFav() {
        List<Object[]> queryResult = favoriteTeamRepository.getMostFav();
        List<GenericStatsDTO> result = new ArrayList<>();
        for (Object[] obj: queryResult){
            GenericStatsDTO tmpFav = new GenericStatsDTO();
            tmpFav.setTeamId(((BigInteger)obj[0]).longValue());
            tmpFav.setCount(((BigInteger)obj[1]).longValue());
            tmpFav.setLeagueId(((BigInteger)obj[2]).longValue());
            tmpFav.setSport(Sport.valueOf(obj[3].toString()));
            result.add(tmpFav);
        }
        return result;
    }
}
