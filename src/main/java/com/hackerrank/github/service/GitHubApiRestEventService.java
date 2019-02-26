package com.hackerrank.github.service;

import com.hackerrank.github.exception.EventNotFoundException;
import com.hackerrank.github.exception.ExistentEventException;
import com.hackerrank.github.model.EventEntity;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.service.converter.domainconverterimpl.EventConverter;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class GitHubApiRestEventService {

    private static final String ACTOR_NOT_FOUND_MESSAGE = "The actor id = {0} can not be found.";
    private static final String EXISTING_EVENT = "The event {0} already exists.";
    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

    @Autowired
    public GitHubApiRestEventService(EventRepository eventRepository, EventConverter eventConverter) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
    }

//    public List<Event> findEventEntityByActorEntity_IdOrderByCreatedAt(Long actorId) {
//        return eventConverter.toDomain(eventRepository.findEventEntityByActorEntity_IdOrderByCreatedAt(actorId));
//    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    public void saveEvent(Event event) {
        if (eventRepository.exists(event.getId())) {
            throw new ExistentEventException(MessageFormat.format(EXISTING_EVENT, event.getId()));
        }

        EventEntity eventEntity = eventConverter.toEntity(event);
        eventRepository.save(eventEntity);
    }

    public List<Event> findAll() {
        return eventConverter.toDomain(eventRepository.findAll());
    }

    public List<Event> findEventsByActor(Long actorID) {
        List<EventEntity> eventEntityList = eventRepository.findByActorId(actorID);

        List<Event> eventList = eventConverter.toDomain(eventEntityList);

        if (eventEntityList.isEmpty()) {
            throw new EventNotFoundException(MessageFormat.format(ACTOR_NOT_FOUND_MESSAGE, actorID));
        }

        return eventList;
    }

}
