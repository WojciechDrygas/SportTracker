package com.ironhack.sporttracker.favoriteservice.service;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.*;
import com.ironhack.sporttracker.favoriteservice.repositories.FavoriteTeamRepository;
import com.ironhack.sporttracker.favoriteservice.repositories.VoteRepository;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final Environment environment;
    private final ModelMapper modelMapper;

    public VoteService(VoteRepository voteRepository, Environment environment, ModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.environment = environment;
        this.modelMapper = modelMapper;
    }

    public VoteResponse putVote(VoteRequest voteRequest, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        //Check if the vote resource exists
        Optional<Vote> vote = voteRepository.findByUserIdAndTeamIdAndSport(tokenSubject, voteRequest.getTeamId(),voteRequest.getSport());
        if (vote.isEmpty()){
            Vote newVote = modelMapper.map(voteRequest,Vote.class);
            newVote.setUserId(tokenSubject);
            Vote savedVote = voteRepository.save(newVote);
            return new VoteResponse(savedVote.getVote(),true);
        }else {
            vote.get().setVote(voteRequest.getVote());
            Vote savedVote =  voteRepository.save(vote.get());
            return new VoteResponse(savedVote.getVote(),false);
        }
    }

    public VotesForTeam getVotesForTeam(Sport sport, Long id) {
        Long likes = voteRepository.countVotesForTeam(id,sport.toString(), 1L);
        Long dislikes = voteRepository.countVotesForTeam(id,sport.toString(),-1L);
        likes=likes==null?0:likes;
        dislikes=dislikes==null?0:dislikes;
        return new VotesForTeam(likes, dislikes, id, sport);
    }

    public Long getCurrentVote(Sport sport, Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Optional<Vote> vote = voteRepository.findByUserIdAndTeamIdAndSport(tokenSubject, id,sport);
        if (vote.isEmpty()){
            return 0L;

        }else {
            return vote.get().getVote();
        }
    }

    public List<GenericStatsDTO> getMost(boolean isLiked) {
        List<Object[]> queryResult;
        if (isLiked){
            queryResult = voteRepository.getMostLiked();
        }else{
            queryResult = voteRepository.getMostDisliked();
        }
        List<GenericStatsDTO> result = new ArrayList<>();
        for (Object[] obj: queryResult){
            GenericStatsDTO genericStats = new GenericStatsDTO();
            Long teamId = new BigDecimal(obj[0].toString()).longValue();
            Long count = new BigDecimal(obj[1].toString()).longValue();
            Long leagueId = new BigDecimal(obj[2].toString()).longValue();
            Sport sport = Sport.valueOf(obj[3].toString());
            genericStats.setTeamId(teamId);
            genericStats.setCount(count);
            genericStats.setLeagueId(leagueId);
            genericStats.setSport(sport);
            result.add(genericStats);
        }
        return result;
    }
}
