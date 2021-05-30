import { React,useEffect,useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailsCard } from '../components/MatchDetailsCard';

export const MatchPage=()=> {
 
  const [matches,setMatches]=useState([]);
  const {teamName, year }=useParams();

  useEffect(
    ()=>{
      const fetchMatches=async ()=>{
          const response= await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
          const data=await response.json();
          console.log(data);
          setMatches(data);
      };
      
      fetchMatches();
    },[teamName,year]
  );
    
  return (
    <div className="MatchPage">
      <h1>Match Page</h1>
      {
        matches.map(match =><MatchDetailsCard teamName={teamName} match={match} />)
      }
    </div>
  );
}
 