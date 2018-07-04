package com.solutions.travel.on.voting_project.controller;

import com.solutions.travel.on.voting_project.dto.PollDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/poll")
public class PollController {



    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PollDTO createPoll(@RequestBody PollDTO pollDTO) {
        return null;
    }

}
