package com.solutions.travel.on.voting_project.dao;

import com.solutions.travel.on.voting_project.model.Answer;
import com.solutions.travel.on.voting_project.model.Poll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PollDAOTest {

    @Autowired
    private PollDAO pollDAO;

    @Test
    public void getPollById() {
    }

    @Test
    public void addPoll() {
        Poll poll = new Poll("Test poll", "What main question about life, universe and so on?", new ArrayList<>(3));
        poll.getAnswers().add(new Answer("test answer 1", poll));
        poll.getAnswers().add(new Answer("test answer 2", poll));
        poll.getAnswers().add(new Answer("test answer 3", poll));

        pollDAO.addPoll(poll);
    }

    @Test
    public void updatePoll() {
        Poll poll = new Poll("Test poll for update", "What main question about life, universe and so on?", new ArrayList<>(3));
        poll.getAnswers().add(new Answer("test answer 1", poll));
        poll.getAnswers().add(new Answer("test answer 2", poll));
        poll.getAnswers().add(new Answer("test answer 3", poll));

        pollDAO.addPoll(poll);

        poll.getAnswers().remove(2);
        int id = poll.getId();
        poll = new Poll("Updated title", "Updated question", poll.getAnswers());
        poll.setId(id);

        pollDAO.updatePoll(poll);

        poll = pollDAO.getPollById(poll.getId());

        assertEquals(poll.getTitle(), "Updated title");
        assertEquals(poll.getQuestion(), "Updated question");
        assertEquals(poll.getAnswers().size(), 2);
    }

    @Test
    public void deletePoll() {
    }
}