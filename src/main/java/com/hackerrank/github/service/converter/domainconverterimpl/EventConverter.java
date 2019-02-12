package com.hackerrank.github.service.converter.domainconverterimpl;

import com.hackerrank.github.model.EventEntity;
import com.hackerrank.github.service.converter.DomainEntityConvert;
import com.hackerrank.github.service.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConverter implements DomainEntityConvert<Event, EventEntity> {

    private ActorConverter actorConverter;
    private RepoConverter repoConverter;

    @Autowired
    public EventConverter(ActorConverter actorConverter, RepoConverter repoConverter) {
        this.actorConverter = actorConverter;
        this.repoConverter = repoConverter;
    }

    @Override
    public EventEntity toEntity(Event domain) {
        return new EventEntity(domain.getId(),
                domain.getType(),
                actorConverter.toEntity(domain.getActor()),
                repoConverter.toEntity(domain.getRepo()),
                domain.getCreatedAt());
    }

    @Override
    public Event toDomain(EventEntity entity) {
        return new Event(entity.getId(),
                entity.getType(),
                actorConverter.toDomain(entity.getActorEntity()),
                repoConverter.toDomain(entity.getRepoEntity()),
                entity.getCreatedAt());
    }
}
