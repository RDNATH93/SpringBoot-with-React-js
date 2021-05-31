import { React,useEffect,useState } from 'react';
import { TeamTile } from '../components/TeamTile';

import './HomePage.scss';

export const HomePage=()=> {

  const [teams,setTeams]=useState([]);
  
  useEffect(
      ()=>{
        const fetchAllTeams=async ()=>{
            const response= await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams/`);
            const data=await response.json();
            //console.log(data);
            setTeams(data);
        };
        
        fetchAllTeams();
      },[]

  );

if(!teams){
  return <h1>Team Not Found</h1>
}
    
  return (
      <div className="HomePage">
        <div className="app-name">
            <h1>IPL DashBoard</h1>
        </div>
        <div className="team-grid">
        {
            teams.map(team=><TeamTile key={team.id} teamName={team.teamName}/>)
        }
        </div>
    </div>
  );    
}
