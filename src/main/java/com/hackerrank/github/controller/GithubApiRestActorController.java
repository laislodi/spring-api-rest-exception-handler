package com.hackerrank.github.controller;

import com.hackerrank.github.service.GitHubApiRestActorService;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GithubApiRestActorController {

    private final GitHubApiRestActorService gitHubApiRestService;

    @Autowired
    public GithubApiRestActorController(GitHubApiRestActorService gitHubApiRestService) {
        this.gitHubApiRestService = gitHubApiRestService;
    }

    @PutMapping("/actors")
    @ResponseBody
    public ResponseEntity updateActor(@RequestBody Actor actor) {
        return gitHubApiRestService.save(actor);
    }

    @GetMapping("/actors")
    public List<Actor> findAllActorsSortByEventNumber() {
        return gitHubApiRestService.findAllActorsSortByNumberOfEvents();
    }

    @GetMapping("/actors/streak")
    public List<Actor> findAllActorsByStreak() {
        return gitHubApiRestService.findAllActorsByStreak();
    }
}
