import { React } from 'react';
import { Link } from 'react-router-dom';

import './MatchDetailsCard.scss';

export const MatchDetailsCard=({teamName,match})=> {
  if(!match) return null;
  const otherTeam= teamName===match.team1?match.team2 :match.team1;
  const otherTeamRoute=`/teams/${otherTeam}`;
  const isMatchWon= teamName===match.matchWinner;

  return (
    <div className={ isMatchWon ? "MatchDetailsCard won-card" : "MatchDetailsCard loss-card"}>
     <div>
        <span>vs</span>
        <h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
        <h2 className="match-date">{match.date}</h2>
        <h3 className="match-venue">at {match.venue}</h3>
        <h4 className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result}</h4>
     </div>
     <div className="additional-detail">
       <h4>First Innings</h4>
       <p>{match.team1}</p>
       <h4>Second Innings</h4>
       <p>{match.team2}</p>
       <h4>Man of the Match</h4>
       <p>{match.playerOfMatch}</p>
       <h4>Umpires</h4>
       <p>{match.umpire1}, {match.umpire2}</p>
     </div>
    </div>
  );
}