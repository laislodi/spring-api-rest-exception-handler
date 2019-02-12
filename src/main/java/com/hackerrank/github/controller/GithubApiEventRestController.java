package com.hackerrank.github.controller;

import com.hackerrank.github.service.GitHubApiRestEventService;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GithubApiEventRestController {

    private final GitHubApiRestEventService gitHubApiRestEventService;

    @Autowired
    public GithubApiEventRestController(GitHubApiRestEventService gitHubApiRestEventService) {
        this.gitHubApiRestEventService = gitHubApiRestEventService;
    }

    @DeleteMapping("/erase")
    @ResponseBody
    public void deleteAllEvents() {
        gitHubApiRestEventService.deleteAllEvents();
    }

    @PostMapping("/create-events")
    public void createAllEvents(@RequestBody List<Event> eventList) {
        gitHubApiRestEventService.createAllEvents(eventList);
    }

    @PostMapping("/events")
    @ResponseBody
    public void saveEvent(@RequestBody Event eventEntity)  {
        gitHubApiRestEventService.saveEvent(eventEntity);
    }

    @GetMapping("/events")
    public List<Event> findAll() {
        return gitHubApiRestEventService.findAll();
    }


    @GetMapping("/events/actors/{actorID}")
    public List<Event> findEventsByActor(@PathVariable("actorID") Long actorID) {
        return gitHubApiRestEventService.findEventsByActor(actorID);
    }

}
