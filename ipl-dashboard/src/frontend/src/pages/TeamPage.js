import { React,useEffect,useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom';
import './TeamPage.scss';

export const TeamPage=()=> {

  const [team,setTeam]=useState({matchPlayed: []});
  const { teamName }=useParams();
  const year=process.env.REACT_APP_DATA_END_YEAR;

  useEffect(
      ()=>{
        const fetchTeam=async ()=>{
            const response= await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}`);
            const data=await response.json();
            //console.log(data);
            setTeam(data);
        };
        
        fetchTeam();
      },[teamName]

  );

if(!team || !team.teamName){
  return <h1>Team Not Found</h1>
}
    
  return (
    <div className="TeamPage">
      <div className="team-name-section">
        <h1 className="team-name">{team.teamName}</h1>
      </div>
      <div className="win-loss-section">
        <p>Wins/Losses</p>
        <PieChart
            data={[
              { title: 'Losses', value: team.totalMatches-team.totalWins, color: '#dc143c' },
              { title: 'Wins', value: team.totalWins, color: '#228b22' },
            ]}
        />
      </div>
      <div className="match-details-section">
      <h3>Latest Matches</h3>
        <MatchDetailsCard  teamName={team.teamName} match={team.matchPlayed[0]}/>
      </div>
      {team.matchPlayed.slice(1).map(match =><MatchSmallCard key={match.id} teamName={team.teamName} match={match}/>)}
      
      <div className="more-link">
        <Link to={`/teams/${team.teamName}/matches/${year}`}>More {'>'} </Link>
      </div>
    </div>
  );
}
