package com.solutions.travel.on.voting_project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id")
    private int id;

    @Column(name = "topic")
    private String topic;

    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Poll() {

    }

    public Poll(String topic, List<Answer> answers) {
        this.topic = topic;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

