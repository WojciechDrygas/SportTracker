import { VotesForTeam } from './../../models/sport-data-models/votes-for-team.model';
import { VotingService } from './../../services/voting/voting.service';
import { Component, Input, OnInit } from '@angular/core';
import { Vote } from 'src/app/models/sport-data-models/vote.model';

@Component({
  selector: 'app-voting',
  templateUrl: './voting.component.html',
  styleUrls: ['./voting.component.css']
})
export class VotingComponent implements OnInit {

  constructor(private votingService:VotingService) { }

  @Input()
  teamId?:number;

  @Input()
  sport?:string;
  @Input()
  leagueId?:number;
  @Input()
  isLoggedIn?:boolean;

  data:VotesForTeam={};
  currentVote:number=0;

  async ngOnInit(): Promise<void> {
    if (this.teamId && this.sport){
      this.votingService.getVoteCount(this.teamId,this.sport).subscribe(resp=>{
        if (resp.body){
          this.data=resp.body;
        }else{
          this.data={
            likes:0,
            dislikes:0
          }
        }
      })
      if (this.isLoggedIn===true){
        this.votingService.getCurrentVote(this.teamId,this.sport).subscribe(resp=>{
          if (resp.body && resp.body.vote){
            this.currentVote=resp.body.vote
          }
        })
      }
    }
  }

  async onVoteClick(isLike:boolean){
    if (this.isLoggedIn===true){
      let voteValue = this.currentVote;
      if (this.currentVote==0 && isLike){
        voteValue=1;
        console.log("Likes:",this.data.likes?true:false);
        this.data.likes = this.data.likes!==undefined?this.data.likes+1:this.data.likes;
      }else if (this.currentVote==0 && !isLike){
        voteValue=-1;
        console.log("Dislikes:",this.data.dislikes);
        this.data.dislikes = this.data.dislikes!==undefined?this.data.dislikes+1:this.data.dislikes;
      }else{
        voteValue = 0;
        this.data.likes = this.data.likes && this.currentVote==1?this.data.likes-1:this.data.likes;
        this.data.dislikes = this.data.dislikes && this.currentVote==-1?this.data.dislikes-1:this.data.dislikes;
      }
      if (this.teamId && this.sport && this.leagueId){
        let vote:Vote={
          vote:voteValue,
          sport:this.sport,
          teamId:this.teamId,
          leagueId:this.leagueId
        }
        this.votingService.putVote(vote).subscribe(resp=>{
          if (resp.body){
            let tmpVote = resp.body.vote;
            this.currentVote=tmpVote?tmpVote:0;
          }
        })
      }
    }

  }


}
