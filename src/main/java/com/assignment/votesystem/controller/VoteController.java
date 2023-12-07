package com.assignment.votesystem.controller;


import com.assignment.votesystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("entercandidate")
    public ResponseEntity<String> enterCandidate(@RequestParam("name") String name){
        return new ResponseEntity<>(voteService.enterCandidate(name), HttpStatus.CREATED);
    }

    @GetMapping("castvote")
    public ResponseEntity<String> castVote(@RequestParam("name") String name){
        return new ResponseEntity<>(voteService.castVote(name), HttpStatus.OK);
    }

    @GetMapping("countvote")
    public ResponseEntity<String> countVote(@RequestParam("name") String name){
        return new ResponseEntity<>(voteService.countVote(name), HttpStatus.OK);
    }
    @GetMapping("listvote")
    public ResponseEntity<Map<String,Integer>> listVote(){
        return new ResponseEntity<>(voteService.listVote(), HttpStatus.OK);
    }

    @GetMapping("getwinner")
    public ResponseEntity<String> getWinner(){
        return new ResponseEntity<>(voteService.getWinner(), HttpStatus.OK);
    }
}
