package com.iplt20.ipldashboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import com.iplt20.ipldashboard.model.MatchSummary;
import com.iplt20.ipldashboard.model.Team;
import com.iplt20.ipldashboard.repository.MatchRepository;
import com.iplt20.ipldashboard.repository.TeamRepository;

@CrossOrigin
@RestController
public class TeamController {
	

	private TeamRepository teamrepository;
	private MatchRepository matchRepository;

	TeamController(TeamRepository teamrepository,MatchRepository matchRepository){
		this.teamrepository=teamrepository;
		this.matchRepository=matchRepository; 
	}
	@GetMapping("/teams/{teamName}")
	public Team getTeamDetails(@PathVariable String teamName) {
		Team team= teamrepository.findByTeamName(teamName);
		team.setMatchPlayed(matchRepository.listMatchesByTeamName(teamName, 4));
		return team;
	}
	@GetMapping("/teams/{teamName}/matches")
	public List<MatchSummary> getMatchesByTeamNameAndYear(@PathVariable String teamName,@RequestParam int year){
		LocalDate startDate=LocalDate.of(year, 1, 1);
		LocalDate endDate=LocalDate.of(year+1, 1, 1);
		return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
	}

}
