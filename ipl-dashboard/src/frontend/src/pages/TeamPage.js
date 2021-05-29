import { React,useEffect,useState } from 'react';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const TeamPage=()=> {

  const [team,setTeam]=useState({matchPlayed: []});

  useEffect(
      ()=>{
        const fetchMatches=async ()=>{
            const response= await fetch('http://localhost:8080/teams/Gujarat%20Lions');
            const data=await response.json();
            console.log(data);
            setTeam(data);
        };
        
        fetchMatches();
      },[]

  );


  return (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>
      <MatchDetailsCard match={team.matchPlayed[0]}/>
      {team.matchPlayed.slice(1).map(match =><MatchSmallCard match={match}/>)}
      
    </div>
  );
}
