package com.hackerrank.github.controller;

import com.hackerrank.github.service.GitHubApiRestEventService;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GithubApiRestEventController {

    private final GitHubApiRestEventService gitHubApiRestEventService;

    @Autowired
    public GithubApiRestEventController(GitHubApiRestEventService gitHubApiRestEventService) {
        this.gitHubApiRestEventService = gitHubApiRestEventService;
    }

    @DeleteMapping("/erase")
    @ResponseBody
    public void deleteAllEvents() {
        gitHubApiRestEventService.deleteAllEvents();
    }

    @DeleteMapping("/erase/actor/{actorID}")
    @ResponseBody
    public ResponseEntity deleteEvents(@PathVariable Long actorID) {
        return gitHubApiRestEventService.deleteEvents(actorID);
    }

    @DeleteMapping("/erase/actor/{repoID}")
    @ResponseBody
    public ResponseEntity deleteRepo(@PathVariable Long repoID) {
        return gitHubApiRestEventService.deleteRepo(repoID);
    }

    @PostMapping("/create-events")
    public void createAllEvents(@RequestBody List<Event> eventList) {
        gitHubApiRestEventService.createAllEvents(eventList);
    }

    @PostMapping("/events")
    @ResponseBody
    public void saveEvent(@RequestBody Event event)  {
        gitHubApiRestEventService.saveEvent(event);
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
