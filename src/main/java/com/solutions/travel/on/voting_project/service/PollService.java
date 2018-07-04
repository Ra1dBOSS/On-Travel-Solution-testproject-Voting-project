package com.solutions.travel.on.voting_project.service;

import com.solutions.travel.on.voting_project.model.Poll;

public interface PollService {

    Poll createPoll(Poll poll);

    Poll readPoll(int id);

    Poll updatePoll(Poll poll);

    void deletePoll(int id);

    Poll startPoll(int id);

    Poll finishPoll(int id);


}
