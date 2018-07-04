package com.solutions.travel.on.voting_project.dao;

import com.solutions.travel.on.voting_project.model.Poll;

public interface PollDAO {

    Poll getPollById(int id);

    void addPoll(Poll poll);

    void updatePoll(Poll poll);

    void deletePoll(int id);

}
