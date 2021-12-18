import { FixtureTeam } from './fixture-team.model';
export interface Fixture{
  event_timestamp?:number;
  round?:string;
  status?:string;
  venue?:string;
  homeTeam:FixtureTeam;
  awayTeam:FixtureTeam;
  goalsHomeTeam?:number;
  goalsAwayTeam?:number;
  eventDate?:string;
}
