package com.solutions.travel.on.voting_project.dao;

import com.solutions.travel.on.voting_project.model.Answer;
import com.solutions.travel.on.voting_project.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PollDAOImpl implements PollDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public Poll getPollById(int id) {
        Poll poll = entityManager.find(Poll.class, id);
        return poll;
    }

    @Override
    public void addPoll(Poll poll) {
        entityManager.persist(poll);
    }

    @Override
    public void updatePoll(Poll poll) {
        Poll _poll = getPollById(poll.getId());
        _poll.setTitle(poll.getTitle());
        _poll.setQuestion(poll.getQuestion());
        _poll.setStatus(poll.getStatus());
        for (Answer x : _poll.getAnswers()) {
            answerDAO.deleteAnswer(x.getId());
        }
        _poll.setAnswers(poll.getAnswers());
        for (Answer x : _poll.getAnswers()) {
            x.setId(0);
            x.setPoll(_poll);
        }
        entityManager.flush();
    }

    @Override
    public void deletePoll(int id) throws IllegalArgumentException {
        entityManager.remove(getPollById(id));
    }
}
