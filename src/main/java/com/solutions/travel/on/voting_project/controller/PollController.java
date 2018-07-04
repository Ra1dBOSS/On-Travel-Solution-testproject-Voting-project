package com.solutions.travel.on.voting_project.controller;

import com.solutions.travel.on.voting_project.dto.PollDTO;
import com.solutions.travel.on.voting_project.model.Poll;
import com.solutions.travel.on.voting_project.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PollDTO createPoll(@RequestBody PollDTO pollDTO) {
        Poll poll = pollService.createPoll(pollDTO.toPoll());
        return new PollDTO(poll);
    }

    @GetMapping("/read/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PollDTO> readPoll(@PathVariable int id) {
        Poll poll = pollService.readPoll(id);
        if (poll == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        PollDTO pollDTO = new PollDTO(poll);
        return ResponseEntity.status(HttpStatus.OK).body(pollDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PollDTO updatePoll(@PathVariable int id, @RequestBody PollDTO pollDTO) {
        Poll poll = pollDTO.toPoll();
        poll.setId(id);
        pollService.updatePoll(poll);
        return new PollDTO(poll);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePoll(@PathVariable int id) {
        try {
            pollService.deletePoll(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
