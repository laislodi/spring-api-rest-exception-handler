package com.hackerrank.github.service;

import com.hackerrank.github.exception.ActorNotFoundException;
import com.hackerrank.github.exception.BadActorUpdateException;
import com.hackerrank.github.model.ActorEntity;
import com.hackerrank.github.model.EventEntity;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.converter.domainconverterimpl.ActorConverter;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GitHubApiRestActorService {

    private final EventRepository eventRepository;
    private final ActorRepository actorRepository;
    private final RepoRepository repoRepository;
    private final ActorConverter actorConverter;

    @Autowired
    public GitHubApiRestActorService(EventRepository eventRepository,
                                     ActorRepository actorRepository,
                                     RepoRepository repoRepository,
                                     ActorConverter actorConverter) {
        this.eventRepository = eventRepository;
        this.actorRepository = actorRepository;
        this.repoRepository = repoRepository;
        this.actorConverter = actorConverter;
    }

    public void save(Actor actor) {
        Long id = actor.getId();
        ActorEntity actorEntity = actorRepository.findOne(id);

        if (actorEntity == null ) {
            throw new ActorNotFoundException("This actor id (id = " + id + ") does not exists.");
        }

        if ( actorEntity.getLogin().equals(actor.getLogin()) && actorEntity.getId().equals(actor.getId()) ) {
            actorEntity.setAvatarUrl(actor.getAvatarUrl());
            actorRepository.save(actorEntity);
        } else {
            throw new BadActorUpdateException("It is not possible to change this actor (id = " + id + ") already exists in the database.");
        }
    }

    public List<Actor> findAllActorsSortByNumberOfEvents() {
        List<ActorEntity> actorEntityList = actorRepository.findAll();

        actorEntityList.sort((a1, a2) -> {
            int size1 = a1.getEventEntityList().size();
            int size2 = a2.getEventEntityList().size();
            int compare = (-1)*Integer.compare(size1, size2);

            if (compare == 0) {
                return orderByCreatedAtDescLoginAsc(a1, a2);
            } else {
                return compare;
            }
        });

        for (ActorEntity e : actorEntityList) {
            List<EventEntity> eventEntityList = e.getEventEntityList();
            eventEntityList.sort(Comparator.comparing(EventEntity::getCreatedAt));
        }

        return actorConverter.toDomain(actorEntityList);
    }

    private int orderByCreatedAtDescLoginAsc(ActorEntity a1, ActorEntity a2) {
        a1.getEventEntityList().sort((e1, e2) -> e2.getCreatedAt().compareTo(e1.getCreatedAt()));
        a2.getEventEntityList().sort((e1, e2) -> e2.getCreatedAt().compareTo(e1.getCreatedAt()));
        EventEntity latestEventEntity1 = a1.getEventEntityList().get(0);
        EventEntity latestEventEntity2 = a2.getEventEntityList().get(0);
        if (latestEventEntity1.getCreatedAt().compareTo(latestEventEntity2.getCreatedAt()) == 0) {
            return a1.getLogin().compareTo(a2.getLogin());
        } else {
            return latestEventEntity2.getCreatedAt().compareTo(latestEventEntity1.getCreatedAt());
        }
    }

    public List<Actor> findAllActorsByStreak() {
        List<ActorEntity> actorEntityList = actorRepository.findAll();

        actorEntityList.sort((a1, a2) -> {
            int maxStreak1 = calculateMaxStreak(a1.getEventEntityList());
            int maxStreak2 = calculateMaxStreak(a2.getEventEntityList());

            if (maxStreak1 == maxStreak2) {
                return orderByCreatedAtDescLoginAsc(a1, a2);
            } else {
                return maxStreak2 - maxStreak1;
            }
        });

        return actorConverter.toDomain(actorEntityList);
    }

    private int calculateMaxStreak(List<EventEntity> eventEntityList) {
        if (eventEntityList.size() == 0) {
            return 0;
        }

        eventEntityList.sort(Comparator.comparing(EventEntity::getCreatedAt));
        int maxStreak = 0;
        int streak = 1;

        LocalDate previousLocalDate = eventEntityList.get(0).getCreatedAt().toLocalDate();
        if (eventEntityList.size() == 1) {
            maxStreak = 1;
        }

        for (int i = 1; i < eventEntityList.size(); i++) {
            LocalDate localDate = eventEntityList.get(i).getCreatedAt().toLocalDate();

            if (localDate.equals(previousLocalDate.plusDays(1))) {
                streak++;
            } else if (localDate.isAfter(previousLocalDate.plusDays(1))) {
                streak = 1;
            } /*else if (localDate == previousLocalDate) {
                continue;
            }*/
            if (streak > maxStreak) {
                maxStreak = streak;
            }
            previousLocalDate = localDate;
        }

        return maxStreak;
    }

    public void deleteEvents(Long actorID) {
        ActorEntity actorEntity = actorRepository.findOne(actorID);
        for (EventEntity e : actorEntity.getEventEntityList()) {
            eventRepository.delete(e.getId());
        }
        actorEntity.setEventEntityList(new ArrayList<>());
    }

    public void deleteRepo(Long repoID) {
        repoRepository.delete(repoID);
    }

}
