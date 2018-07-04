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
                maxI = i;
                leader = pollDTO.getAnswers()[i].getText();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Number of voters is ").append(votes).append("\n");
        stringBuilder.append("Leader is answer number ").append(maxI).append(" :").append(leader).append("\n")
                .append("With ").append(max);
        if (allVoted > 0) {
            stringBuilder.append("(").append(max / allVoted).append(")");
        }
        stringBuilder.append(" number of votes").append("\n\n\n");
        for (int i = 0; i<pollDTO.getAnswers().length; i++) {
            stringBuilder.append(pollDTO.getAnswers()[i].getStatistic());
            if (allVoted > 0) {
                stringBuilder.append("(").append(pollDTO.getAnswers()[i].getStatistic() / allVoted).append(")");
            }
            stringBuilder.append(" votes for number ").append(i).append(" :").append(pollDTO.getAnswers()[i].getText()).append("\n");
        }
        return stringBuilder.toString();
    }

}
