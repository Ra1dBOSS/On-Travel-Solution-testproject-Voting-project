package com.solutions.travel.on.voting_project.dao;

import com.solutions.travel.on.voting_project.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AnswerDAOImpl implements AnswerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Answer getAnswerById(int id) {
        return entityManager.find(Answer.class, id);
    }

    @Override
    public void deleteAnswer(int id) {
        entityManager.remove(getAnswerById(id));
        entityManager.getTransaction().commit();
    }
}
