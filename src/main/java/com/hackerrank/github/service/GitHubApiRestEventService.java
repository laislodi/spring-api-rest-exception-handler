package com.hackerrank.github.service;

import com.hackerrank.github.exception.ExistentEventException;
import com.hackerrank.github.exception.NoActorEventsFoundException;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.repository.model.EventEntity;
import com.hackerrank.github.service.converter.impl.EventConverter;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GitHubApiRestEventService {

    private final EventRepository eventRepository;

    private final EventConverter eventConverter;

    @Autowired
    public GitHubApiRestEventService(EventRepository eventRepository,
                                     EventConverter eventConverter) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    public ResponseEntity deleteEvents(Long actorID) {
        return null;
    }

    public ResponseEntity deleteRepo(Long repoID) {
        return null;
    }

    public List<Event> findEventsByActor(Long actorID) {
        if (eventRepository.findByActorEntityId(actorID).size() != 0) {
            return eventConverter.toDomain(eventRepository.findByActorEntityId(actorID));
        } else {
            throw new NoActorEventsFoundException();
        }
    }

    public void createAllEvents(List<Event> eventList) {
    }

    public void saveEvent(Event event) {
        Event e = eventConverter.toDomain(eventRepository.findOne(event.getId()));
        if (findAllInList().contains(e)) {
            throw new ExistentEventException();
        }
        eventRepository.save(eventConverter.toEntity(event));
    }

    public List<Event> findAll() {
        return findAllInList();
    }


    /**
     * Private Methods
     */
    private List<Event> findAllInList() {
        List<EventEntity> eventEntities = eventRepository.findAll();
        List<Event> all = eventConverter.toDomain(eventEntities);
        all.sort(Comparator.comparing(Event::getId));
        return all;
    }
}
