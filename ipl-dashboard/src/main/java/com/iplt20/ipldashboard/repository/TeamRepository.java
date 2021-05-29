package com.iplt20.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.iplt20.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
       
	public Team findByTeamName(String teamName);
	
}
