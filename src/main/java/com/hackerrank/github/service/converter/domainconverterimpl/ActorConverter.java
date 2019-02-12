package com.hackerrank.github.service.converter.domainconverterimpl;

import com.hackerrank.github.model.ActorEntity;
import com.hackerrank.github.service.converter.DomainEntityConvert;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter implements DomainEntityConvert<Actor, ActorEntity> {

    @Override
    public ActorEntity toEntity(Actor actor) {
        return new ActorEntity(actor.getId(),
                actor.getLogin(),
                actor.getAvatarUrl());
    }

    @Override
    public Actor toDomain(ActorEntity actorEntity) {
        return new Actor(actorEntity.getId(),
                actorEntity.getLogin(),
                actorEntity.getAvatarUrl());
    }
}
