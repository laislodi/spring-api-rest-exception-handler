package com.hackerrank.github.service.converter.impl;

import com.hackerrank.github.repository.model.ActorEntity;
import com.hackerrank.github.repository.model.EventEntity;
import com.hackerrank.github.repository.model.RepoEntity;
import com.hackerrank.github.service.converter.DomainConverter;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConverter implements DomainConverter<Event, EventEntity> {

    private final ActorConverter actorConverter;
    private final RepoConverter repoConverter;

    @Autowired
    public EventConverter(ActorConverter actorConverter, RepoConverter repoConverter) {
        this.actorConverter = actorConverter;
        this.repoConverter = repoConverter;
    }

    @Override
    public Event toDomain(EventEntity entity) {
        return new Event(entity.getId(), entity.getType(), entity.getCreatedAt());
    }

    @Override
    public EventEntity toEntity(Event domain) {
        ActorEntity actorEntity = actorConverter.toEntity(domain.getActor());
        RepoEntity repoEntity = repoConverter.toEntity(domain.getRepo());

        return new EventEntity(domain.getId(), domain.getType(), actorEntity, repoEntity, domain.getCreatedAt());
    }
}
