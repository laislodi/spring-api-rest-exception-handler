package com.hackerrank.github.controller;

import com.hackerrank.github.service.GitHubApiRestActorService;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("")
public class GithubApiActorRestController {

    private final GitHubApiRestActorService gitHubApiRestActorService;

    @Autowired
    public GithubApiActorRestController(GitHubApiRestActorService gitHubApiRestActorService) {
        this.gitHubApiRestActorService = gitHubApiRestActorService;
    }

    @DeleteMapping("/erase/actor/{actorID}")
    @ResponseBody
    public void deleteEvents(@PathVariable Long actorID) {
        gitHubApiRestActorService.deleteEvents(actorID);
    }

    @DeleteMapping("/erase/actor/{repoID}")
    @ResponseBody
    public void deleteRepo(@PathVariable Long repoID) {
        gitHubApiRestActorService.deleteRepo(repoID);
    }

    @PutMapping("/actors")
    @ResponseBody
    public void updateActor(@RequestBody Actor actor) { gitHubApiRestActorService.save(actor);
    }

    @GetMapping("/actors")
    public List<Actor> findAllActorsSortByEventNumber() {
        return gitHubApiRestActorService.findAllActorsSortByNumberOfEvents();
    }

    @GetMapping("/actors/streak")
    public List<Actor> findAllActorsByStreak() {
        return gitHubApiRestActorService.findAllActorsByStreak();
    }
}
