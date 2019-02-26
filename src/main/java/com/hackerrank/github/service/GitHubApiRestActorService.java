package com.hackerrank.github.service;

import com.hackerrank.github.exception.ActorNotFoundException;
import com.hackerrank.github.exception.BadActorUpdateException;
import com.hackerrank.github.model.ActorEntity;
import com.hackerrank.github.model.EventEntity;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.converter.domainconverterimpl.ActorConverter;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class GitHubApiRestActorService {

    private static final String ACTOR_NOT_EXISTS_MESSAGE = "This actor id (id = {0}) does not exists.";
    private static final String ACTOR_ALREADY_EXISTS_MESSAGE = "It is not possible to change this actor (id = {0}) " +
            "already exists in the database.";

    private final ActorRepository actorRepository;
    private final RepoRepository repoRepository;

    private final ActorConverter actorConverter;

    @Autowired
    public GitHubApiRestActorService(ActorRepository actorRepository,
            RepoRepository repoRepository, ActorConverter actorConverter) {
        this.actorRepository = actorRepository;
        this.repoRepository = repoRepository;
        this.actorConverter = actorConverter;
    }

    public void save(Actor actor) {
        Long id = actor.getId();
        ActorEntity actorEntity = actorRepository.findOne(id);

        if (Objects.isNull(actorEntity)) {
            throw new ActorNotFoundException(MessageFormat.format(ACTOR_NOT_EXISTS_MESSAGE, id));
        }

        if ( actorEntity.getLogin().equals(actor.getLogin())) {
            actorEntity.setAvatarUrl(actor.getAvatarUrl());
            actorRepository.save(actorEntity);
        } else {
            throw new BadActorUpdateException(MessageFormat.format(ACTOR_ALREADY_EXISTS_MESSAGE, id));
        }
    }

    public List<Actor> findAllActorsSortByNumberOfEvents() {
        return actorConverter.toDomain(actorRepository.allActorsOrderByNumberOfEventsDescMaxCreatedAtDescLoginAsc());
    }

    // Ordenado no backend
    public List<Actor> findAllActorsSortByNumberOfEvents2() {
        /**
         * Select all the elements from ActorEntity (having an event list or not - left join)
         *
         * select a from ActorEntity a
         *   left join a.events e
         *  group by a.id, a.login, a.avatarUrl
         *  order by count(e.id) desc, max(e.createdAt) desc, a.login asc
         *
         * */
        List<ActorEntity> actorEntityList = actorRepository.findAll();

        actorEntityList.forEach(actorEntity -> actorEntity
                .getEventEntityList()
                .sort(Comparator.comparing(EventEntity::getCreatedAt).reversed()));

        actorEntityList.sort(Comparator
                .comparing((ActorEntity actorEntity) -> actorEntity.getEventEntityList().size())
                .thenComparing(actorEntity -> actorEntity.getEventEntityList().get(0).getCreatedAt())
                .reversed()
                .thenComparing(ActorEntity::getLogin));

        return actorConverter.toDomain(actorEntityList);
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

    private int calculateMaxStreak(List<EventEntity> eventEntityList) {
        int size = eventEntityList.size();

        if (size < 2) {
            return size;
        }

        eventEntityList.sort(Comparator.comparing(EventEntity::getCreatedAt));

        int maxStreak = 1;
        int streak = 1;

        LocalDate previousLocalDate = eventEntityList.get(0).getCreatedAt().toLocalDate();

        for (int i = 1; i < size; i++) {
            LocalDate localDate = eventEntityList.get(i).getCreatedAt().toLocalDate();

            if (previousLocalDate.equals(localDate.minusDays(1))){
                streak++;
            } else {
                maxStreak = Math.max(streak, maxStreak);
                streak = 1;
            }
            previousLocalDate = localDate;
        }

        return Math.max(streak, maxStreak);
    }

    public void deleteRepo(Long repoID) {
        repoRepository.delete(repoID);
    }

}
