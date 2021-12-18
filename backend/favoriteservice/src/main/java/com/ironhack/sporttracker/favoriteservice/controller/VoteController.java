package com.ironhack.sporttracker.favoriteservice.controller;

import com.ironhack.sporttracker.favoriteservice.enums.Sport;
import com.ironhack.sporttracker.favoriteservice.model.GenericStatsDTO;
import com.ironhack.sporttracker.favoriteservice.model.VoteRequest;
import com.ironhack.sporttracker.favoriteservice.model.VoteResponse;
import com.ironhack.sporttracker.favoriteservice.model.VotesForTeam;
import com.ironhack.sporttracker.favoriteservice.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PutMapping("/vote")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public VoteResponse putVote(@RequestBody VoteRequest voteRequest, @RequestHeader(name = "Authorization") String token){
        return voteService.putVote(voteRequest, token);
    }
    @GetMapping("/vote/{sport}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VoteResponse getCurrentVote(@PathVariable Sport sport, @PathVariable Long id,@RequestHeader(name = "Authorization") String token){
        Long vote = voteService.getCurrentVote(sport, id,token);
        return new VoteResponse(vote,false);
    }

    @GetMapping("/votes/{sport}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VotesForTeam getVotesForTeam(@PathVariable Sport sport, @PathVariable Long id){
        return voteService.getVotesForTeam(sport,id);
    }

    @GetMapping("/inner/stats/likes")
    @ResponseStatus(HttpStatus.OK)
    public List<GenericStatsDTO> getMostLiked(){
        return voteService.getMost(true);
    }
    @GetMapping("/inner/stats/dislikes")
    @ResponseStatus(HttpStatus.OK)
    public List<GenericStatsDTO> getMostDisliked(){
        return voteService.getMost(false);
    }
}
