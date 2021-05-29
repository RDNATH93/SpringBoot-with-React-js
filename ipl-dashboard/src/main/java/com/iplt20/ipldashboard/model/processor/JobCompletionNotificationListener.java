package com.iplt20.ipldashboard.model.processor;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iplt20.ipldashboard.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager em;

  @Autowired
  public JobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

		/*
		 * jdbcTemplate.query("SELECT team1,team2,date FROM match_summary", (rs, row) ->
		 * "Team1: "+rs.getString(1)+" Team2: "+rs.getString(2)+" Date: "+rs.getString(
		 * 3) ).forEach(str->System.out.println(str));
		 */
      Map<String,Team>teamData=new HashMap<>();
      
      em.createQuery("select m.team1 ,count(*) from MatchSummary m group by m.team1",Object[].class)
      .getResultStream()
      .forEach(obj->teamData.put((String)obj[0] ,new Team((String)obj[0],(long)obj[1])));
      
      em.createQuery("select m.team2 ,count(*) from MatchSummary m group by m.team2",Object[].class)
      .getResultStream()
      .forEach(obj->{
    	Team team=teamData.get((String)obj[0]);
    	if(team!=null) {
    		team.setTotalMatches(team.getTotalMatches()+(long)obj[1]);
    	}
      });
      
      em.createQuery("select m.matchWinner ,count(*) from MatchSummary m group by m.matchWinner",Object[].class)
      .getResultStream()
      .forEach(obj->{
    	  Team team=teamData.get((String)obj[0]);
    	  if(team!=null) {
      		team.setTotalWins((long)obj[1]);
      	}
      });
      
      teamData.values().forEach(team->em.persist(team));
      teamData.values().forEach(team->System.out.println(team));
    }
  }
}