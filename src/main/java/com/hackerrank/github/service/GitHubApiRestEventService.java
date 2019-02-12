package com.hackerrank.github.service;

import com.hackerrank.github.exception.EventNotFoundException;
import com.hackerrank.github.exception.ExistentEventException;
import com.hackerrank.github.model.EventEntity;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.converter.domainconverterimpl.EventConverter;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class GitHubApiRestEventService {

    private final EventRepository eventRepository;
    private final ActorRepository actorRepository;
    private final RepoRepository repoRepository;
    private final EventConverter eventConverter;

    @Autowired
    public GitHubApiRestEventService(EventRepository eventRepository,
                                     ActorRepository actorRepository,
                                     RepoRepository repoRepository,
                                     EventConverter eventConverter) {
        this.eventRepository = eventRepository;
        this.actorRepository = actorRepository;
        this.repoRepository = repoRepository;
        this.eventConverter = eventConverter;
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    public void saveEvent(Event event) {
        EventEntity e = eventRepository.findOne(event.getId());

        if (Objects.nonNull(e)) {
            throw new ExistentEventException("O evento " + e.getId() + " já existe.");// 400 bad request ok
        }

        EventEntity eventEntity = eventConverter.toEntity(event);
        eventRepository.save(eventEntity);
    }

    private List<Event> findAllInList() {
        List<EventEntity> all = eventRepository.findAll();
        List<Event> eventList = eventConverter.toDomain(all);

        eventList.sort(Comparator.comparing(Event::getId));

        return eventList;
    }

    public void createAllEvents(List<Event> eventList) {
        List<EventEntity> eventEntityList =  eventConverter.toEntity(eventList);

        for ( EventEntity e : eventEntityList) {
//            LocalDateTime createdAt = e.getCreatedAt();
//
//            new Timestamp(createdAt.getYear(), createdAt.getMonthValue(), createdAt.getDayOfMonth(),
//                    createdAt.getHour(), createdAt.getMinute(), createdAt.getSecond(), 0);
            actorRepository.save(e.getActorEntity());
            repoRepository.save(e.getRepoEntity());
            eventRepository.save(e);
        }
    }

    public List<Event> findAll() {
        return eventConverter.toDomain(eventRepository.findAll());
    }
    public List<Event> findEventsByActor(Long actorID) {
        List<EventEntity> eventEntityList = eventRepository.findByActorId(actorID);

        List<Event> eventList;
        eventList = eventConverter.toDomain(eventEntityList);

        if (eventEntityList.size() == 0) {
            throw new EventNotFoundException("The actor id = " + actorID + " can not be found."); // 404 not found ok
        }
        return eventList;
    }

}
