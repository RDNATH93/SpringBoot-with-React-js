package com.iplt20.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.iplt20.ipldashboard.model.MatchSummary;

public interface MatchRepository extends CrudRepository<MatchSummary,Long> {

	List<MatchSummary> getByTeam1OrTeam2OrderByDateDesc(String teamName1,String teamName2,Pageable pageable);
	
	default List<MatchSummary> listMatchesByTeamName(String teamName,int count){
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
	}
}
