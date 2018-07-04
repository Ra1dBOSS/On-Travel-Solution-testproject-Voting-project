package com.solutions.travel.on.voting_project.dao;

import com.solutions.travel.on.voting_project.model.Answer;
import com.solutions.travel.on.voting_project.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

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
        Poll poll1 = getPollById(poll.getId());
        poll1.setTitle(poll.getTitle());
        poll1.setQuestion(poll.getQuestion());
        for (Answer x : poll1.getAnswers()) {
            answerDAO.deleteAnswer(x.getId());
        }
        poll1.setAnswers(poll.getAnswers());
        for (Answer x : poll1.getAnswers()) {
            x.setId(0);
        }
        entityManager.flush();
    }

    @Override
    public void deletePoll(int id) {
        entityManager.remove(getPollById(id));
    }
}
