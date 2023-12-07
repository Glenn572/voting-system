package com.assignment.votesystem.service;


import com.assignment.votesystem.exception.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class VoteService {

   Map<String,Integer> candidateList= new ConcurrentHashMap<>();
    public String enterCandidate(String name) {
      try{
          if(!candidateList.containsKey(name)){
              int voteCount=0;
              candidateList.put(name,voteCount);
          }
          else{
              throw new GenericException("There is already a candidate is exist in this name,so please choose any other name", HttpStatus.BAD_REQUEST);
          }
      }
      catch(Exception e){
          throw new GenericException("Unable to add your name as a candidate as this candidate name is already exists", HttpStatus.BAD_REQUEST);
        }
        return "Congratulations! You have successfully registered yourself in our voting system";
    }

    public String castVote(String name) {

        try{
            if(candidateList.containsKey(name)){
                candidateList.put(name,candidateList.getOrDefault(name,0)+1);

            }
            else{
              throw new GenericException("Sorry! There is no candidate belongs to this name so please register yourself first" + name , HttpStatus.BAD_REQUEST);

            }
        }
        catch(Exception e){
            throw new GenericException("Unable to add your vote as this name does not exists in our candidate list", HttpStatus.BAD_REQUEST);
        }
        return "Congratulations! You have successfully completed your vote.";
    }

    public String countVote(String name) {

        try{
            if(candidateList.containsKey(name)){
                int voteCount=candidateList.get(name);
               return "Your latest vote count is "+ voteCount ;
            }
            else{
                throw new GenericException("Sorry! There is no candidate belongs to this name " + name , HttpStatus.BAD_REQUEST);

            }
        }
        catch(Exception e){
            throw new GenericException("Unable to count your vote", HttpStatus.BAD_REQUEST);
        }
    }

    public Map<String, Integer> listVote() {
        try{
           return candidateList;
        }
        catch(Exception e){
            throw new GenericException("Unable to list all the candidate and vote counts due to excessive requests.Kindly Please wait and retry after some time", HttpStatus.BAD_REQUEST);
        }
    }

    public String getWinner() {
        try{
            List<String> winners = candidateList.entrySet()
                    .stream()
                    .collect(Collectors.groupingBy(Map.Entry::getValue))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElse(null)
                    .stream()
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            return winners.size() > 1 ? "Vote draw!. Please check the result after some time" : "Winner is " + winners.get(0);

        }
        catch(Exception e){
            throw new GenericException("Unable to get the winner because we are still counting the vote.Please try again after some time", HttpStatus.BAD_REQUEST);
        }
    }
}
