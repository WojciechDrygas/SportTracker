import { GamesSummary } from './games-summary.model';
export interface Standing{
  rank?:number;
  teamName?:string;
  forme?:string;
  all:GamesSummary;
  home:GamesSummary;
  away:GamesSummary;
  goalsDiff?:number;
  points?:number;
  team_id?:number;
  logo?:string;
  conference?:string;
}
