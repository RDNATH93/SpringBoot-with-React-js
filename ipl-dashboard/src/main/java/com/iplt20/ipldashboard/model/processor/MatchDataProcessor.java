package com.iplt20.ipldashboard.model.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import com.iplt20.ipldashboard.data.MatchInput;
import com.iplt20.ipldashboard.model.MatchSummary;

public class MatchDataProcessor implements ItemProcessor<MatchInput, MatchSummary>{

	//private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public MatchSummary process(final MatchInput matchInput) throws Exception {
	    MatchSummary summary=new MatchSummary();
	    summary.setId(Long.parseLong(matchInput.getId()));
	    summary.setCity(matchInput.getCity());
	    summary.setDate(LocalDate.parse(matchInput.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    summary.setPlayerOfMatch(matchInput.getPlayer_of_match());
	    summary.setVenue(matchInput.getVenue());
	    
	    String firstInningsTeam=matchInput.getTeam1();
	    String secondInningsTeam=matchInput.getTeam2();
	    
	    if((firstInningsTeam.equals(matchInput.getToss_winner()) &&
	    		matchInput.getToss_decision().equals("field"))||
	    		(secondInningsTeam.equals(matchInput.getToss_winner()) &&
	    	    		matchInput.getToss_decision().equals("bat"))){
	    	summary.setTeam1(secondInningsTeam);
	    	summary.setTeam2(firstInningsTeam);
	    }else {
	    	summary.setTeam1(firstInningsTeam);
	    	summary.setTeam2(secondInningsTeam);
	    }
	    
	    summary.setTossWinner(matchInput.getToss_winner());
	    summary.setTossDecision(matchInput.getToss_decision());
	    summary.setMatchWinner(matchInput.getWinner());
	    summary.setResult(matchInput.getResult());
	    summary.setResultMargin(matchInput.getResult_margin());
	    summary.setUmpire1(matchInput.getUmpire1());
	    summary.setUmpire2(matchInput.getUmpire2());
	    return summary;
	  }
}
