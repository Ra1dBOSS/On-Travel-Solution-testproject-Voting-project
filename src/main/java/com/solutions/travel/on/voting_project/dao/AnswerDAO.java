package com.solutions.travel.on.voting_project.dao;

import com.solutions.travel.on.voting_project.model.Answer;

public interface AnswerDAO {

    Answer getAnswerById(int id);

    void deleteAnswer(int id);

}
