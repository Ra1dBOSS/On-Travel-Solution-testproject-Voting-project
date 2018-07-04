package com.solutions.travel.on.voting_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solutions.travel.on.voting_project.model.Answer;
import com.solutions.travel.on.voting_project.model.Poll;
import com.solutions.travel.on.voting_project.model.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PollDTO {

    @JsonIgnore
    private int id;

    private String title;
    private String question;
    private Status status;
    private Answer[] answers;

    public PollDTO() {

    }

    public PollDTO(Poll poll) {
        this.id = poll.getId();
        this.title = poll.getTitle();
        this.question = poll.getQuestion();
        this.status = poll.getStatus();
        this.answers = new Answer[poll.getAnswers().size()];
        int i = 0;
        for (Answer x : poll.getAnswers()) {
            this.answers[i] = x;
            i++;
        }
    }

    @JsonIgnore
    public Poll toPoll() {
        Poll poll = new Poll();
        poll.setId(id);
        poll.setTitle(title);
        poll.setQuestion(question);
        poll.setAnswers(Arrays.asList(answers));
        return poll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Href[] getHrefs() {
        List<Href> hrefs = new ArrayList<>();
        if (status == Status.CREATED) {
            hrefs.add(new Href("start this poll", "/poll/start/" + id));
        }
        if (status == Status.STARTED) {
            hrefs.add(new Href("finish this poll", "/poll/finish/" + id));
            for (int i = 0; i<answers.length; i++) {
                hrefs.add(new Href("vote for answer number " + i + " :" + answers[i].getText()
                        , "/poll/vote/" + id + "/" + answers[i].getId()));
            }
        }
        if (status == Status.FINISHED) {
            hrefs.add(new Href("check out statistic", "/poll/stat/" + id));
        }
        hrefs.add(new Href("create new poll", "/poll/create/"));
        hrefs.add(new Href("get this poll", "/poll/read/" + id));
        hrefs.add(new Href("update this poll", "/poll/update/" + id));
        hrefs.add(new Href("delete this poll", "/poll/delete/" + id));
        Href[] hrefDTOs = new Href[hrefs.size()];
        int i = 0;
        for (Href x : hrefs) {
            hrefDTOs[i] = x;
            i++;
        }
        return hrefDTOs;
    }

    class Href {

        private String name;
        private String href;

        public Href() {

        }

        public Href(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
