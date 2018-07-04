package com.solutions.travel.on.voting_project.service;

import com.solutions.travel.on.voting_project.dao.PollDAO;
import com.solutions.travel.on.voting_project.model.Answer;
import com.solutions.travel.on.voting_project.model.Poll;
import com.solutions.travel.on.voting_project.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollDAO pollDAO;

    @Override
    public Poll createPoll(Poll poll) {
        for (Answer x : poll.getAnswers()) {
            x.setPoll(poll);
            x.setStatistic(0);
        }
        pollDAO.addPoll(poll);
        return poll;
    }

    @Override
    public Poll readPoll(int id) {
        Poll poll = pollDAO.getPollById(id);
        return poll;
    }

    @Override
    public Poll updatePoll(Poll poll) {
        pollDAO.updatePoll(poll);
        return poll;
    }

    @Override
    public void deletePoll(int id) throws IllegalArgumentException {
        pollDAO.deletePoll(id);
    }

    @Override
    public Poll startPoll(int id) {
        Poll poll = pollDAO.getPollById(id);
        if ((poll != null) && (poll.getStatus() == Status.CREATED)) {
            poll.setStatus(Status.STARTED);
            pollDAO.addPoll(poll);
        }
        return poll;
    }

    @Override
    public Poll finishPoll(int id) {
        Poll poll = pollDAO.getPollById(id);
        if ((poll != null) && (poll.getStatus() == Status.STARTED)) {
            poll.setStatus(Status.FINISHED);
            pollDAO.addPoll(poll);
        }
        return poll;
    }

    @Override
    public Poll vote(int pollId, int answerId) {
        Poll poll = pollDAO.getPollById(pollId);
        if ((poll != null) && (poll.getStatus().equals(Status.STARTED))) {
            for (Answer x : poll.getAnswers()) {
                if (x.getId() == answerId) {
                    x.setStatistic(x.getStatistic() + 1);
                    break;
                }
            }
            pollDAO.addPoll(poll);
        }
        return poll;
    }
}
