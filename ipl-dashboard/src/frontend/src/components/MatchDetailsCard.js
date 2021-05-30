import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailsCard=({teamName,match})=> {
  if(!match) return null;
  const otherTeam= teamName===match.team1?match.team2 :match.team1;
  const otherTeamRoute=`/teams/${otherTeam}`;

  return (
    <div className="MatchDetailsCard">
     <h3>Latest Matches</h3>
      
     <h1>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h1>
     <h2>{match.date}</h2>
     <h3>at {match.venue}</h3>
     <h4>{match.matchWinner} won by {match.resultMargin} {match.result}</h4>
    </div>
  );
}