package com.hackerrank.github.service.converter.impl;

import com.hackerrank.github.repository.model.ActorEntity;
import com.hackerrank.github.service.converter.DomainConverter;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter implements DomainConverter<Actor, ActorEntity> {
    @Override
    public Actor toDomain(ActorEntity entity) {
        return new Actor(entity.getId(), entity.getLogin(), entity.getAvatarUrl());
    }

    @Override
    public ActorEntity toEntity(Actor domain) {
        return new ActorEntity(domain.getId(), domain.getLogin(), domain.getAvatarUrl());
    }
}
