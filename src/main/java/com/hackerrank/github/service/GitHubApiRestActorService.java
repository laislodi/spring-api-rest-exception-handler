package com.hackerrank.github.service;

import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.model.ActorEntity;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubApiRestActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public GitHubApiRestActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public ResponseEntity deleteEvents(Long actorID) {
        return null;
    }

    public ResponseEntity save(Actor actor) {
        ActorEntity a = actorRepository.findOne(actor.getId());

        if (a == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (a.getLogin().equals(actor.getLogin()) && a.getId().equals(actor.getId())) {
            actorRepository.save(a);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deleteRepo(Long repoID) {
        return null;
    }

    public List<Actor> findAllActorsSortByNumberOfEvents() {
        List<ActorEntity> entityList = actorRepository.findAll();
        List<Actor> actorList = new ArrayList<>();

        entityList.sort((a1, a2) -> {
            int size1 = a1.getEventList().size();
            int size2 = a2.getEventList().size();
            int compare = (-1)*Integer.compare(size1, size2);

            if (compare == 0) {
                return orderByCreatedAtDescLoginAsc(a1, a2);
            } else {
                return compare;
            }
        });

        for (ActorEntity entity : entityList) {
            Actor actor = new Actor(entity.getId(), entity.getLogin(), entity.getAvatarUrl());
            actorList.add(actor);
        }

        return actorList;
    }

    public List<Actor> findAllActorsByStreak() {
        return null;
    }

    /**
     * Private Methods
     */

    private int orderByCreatedAtDescLoginAsc(ActorEntity a1, ActorEntity a2) {
        return 0;
    }
}
