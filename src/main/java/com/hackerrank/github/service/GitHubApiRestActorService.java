package com.hackerrank.github.service;

import com.hackerrank.github.exception.ActorNotFoundException;
import com.hackerrank.github.exception.BadActorUpdateException;
import com.hackerrank.github.exception.ExistentActorException;
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
import java.time.LocalDateTime;
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
            return;
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
//            System.out.println(a1.getLogin());
//            System.out.println(maxStreak1);
            int maxStreak2 = calculateMaxStreak(a2.getEventEntityList());
//            System.out.println(a2.getLogin());
//            System.out.println(maxStreak2);

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



    public static void main(String[] args) {
        GitHubApiRestActorService service = new GitHubApiRestActorService(null,null,null, null);

        //
        List<ActorEntity> actorEntities = new ArrayList<>();
        actorEntities.add(new ActorEntity(1087339L, "ktran", "https://avatars.com/1087339"));
        actorEntities.add(new ActorEntity(1852220L, "jessicawilkinson", "https://avatars.com/1852220"));
        List<EventEntity> e = new ArrayList<>();
        EventEntity e1 = new EventEntity(1L, "", actorEntities.get(0), null, LocalDateTime.of(2019,8,3,14,30));
        EventEntity e2 = new EventEntity(2L, "", actorEntities.get(1), null, LocalDateTime.of(2015,12,28, 13,0));
        EventEntity e3 = new EventEntity(3L, "", actorEntities.get(1), null, LocalDateTime.of(2019, 4, 2, 13, 30));
        e.add(e1);
        e.add(e2);
        e.add(e3);
        ArrayList<EventEntity> list0 = new ArrayList<>();
        list0.add(e1);
        ArrayList<EventEntity> list1 = new ArrayList<>();
        list1.add(e2);
        list1.add(e3);
        actorEntities.get(0).setEventEntityList(list0);
        actorEntities.get(1).setEventEntityList(list1);

        actorEntities.sort((a1, a2) -> {
            int maxStreak1 = service.calculateMaxStreak(a1.getEventEntityList());
            System.out.println(a1.getLogin());
            System.out.println(maxStreak1);
            int maxStreak2 = service.calculateMaxStreak(a2.getEventEntityList());
            System.out.println(a2.getLogin());
            System.out.println(maxStreak2);

            if (maxStreak1 == maxStreak2) {
                return service.orderByCreatedAtDescLoginAsc(a1, a2);
            } else {
                return maxStreak2 - maxStreak1;
            }
        });

        System.out.println();

        for (ActorEntity actorEntity : actorEntities) {
            System.out.println(actorEntity.getLogin());
        }
    }
}
