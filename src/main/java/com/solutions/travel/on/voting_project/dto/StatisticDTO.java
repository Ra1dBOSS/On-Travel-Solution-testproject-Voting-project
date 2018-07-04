package com.solutions.travel.on.voting_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solutions.travel.on.voting_project.model.Poll;
import com.solutions.travel.on.voting_project.model.Status;

public class StatisticDTO {

    @JsonProperty("poll information")
    private PollDTO pollDTO;

    public StatisticDTO(Poll poll) {
        this.pollDTO = new PollDTO(poll);
    }

    @JsonProperty("poll statistic")
    public String getStatistic() {
        if (pollDTO.getStatus().equals(Status.CREATED))
            return "Poll is not started";
        int allVoted = 0;
        int[] votes = new int[pollDTO.getAnswers().length];
        String leader = null;
        int max = -1;
        int maxI = 0;
        for (int i = 0; i<pollDTO.getAnswers().length; i++) {
            allVoted += pollDTO.getAnswers()[i].getStatistic();
            votes[i] = pollDTO.getAnswers()[i].getStatistic();
            if (pollDTO.getAnswers()[i].getStatistic() > max) {
                max = pollDTO.getAnswers()[i].getStatistic();
                maxI = pollDTO.getAnswers()[i].getId();
                leader = pollDTO.getAnswers()[i].getText();
            }
        }
        String ret ="Number of all voted is " + allVoted + "\nLeader is answer with id " + maxI + ": " + leader + "\n" +  "With " + max;
        if (allVoted > 0) {
            ret += "(" + max / allVoted + ")";
        }
        ret += " number of votes\n\n";
        for (int i = 0; i<pollDTO.getAnswers().length; i++) {
            ret += pollDTO.getAnswers()[i].getStatistic();
            if (allVoted > 0) {
                ret += "(" + pollDTO.getAnswers()[i].getStatistic() / allVoted + ")";
            }
            ret += " votes for number " + i + " :" + pollDTO.getAnswers()[i].getText() + "\n";
        }
        return ret;
    }

}
