import { React } from 'react';
import { Link } from 'react-router-dom';

import './MatchSmallCard.scss';

export const MatchSmallCard=({teamName,match})=> {
  const otherTeam= teamName===match.team1?match.team2 :match.team1;
  const otherTeamRoute=`/teams/${otherTeam}`;
  const isMatchWon= teamName===match.matchWinner;

  return (
    <div className={isMatchWon ? "MatchSmallCard won-card" :" MatchSmallCard loss-card"}>
        <span>vs </span>
        <h4><Link to={otherTeamRoute}>{otherTeam}</Link></h4>
        <p className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result}</p>
    </div>
  );
}

