package com.hackerrank.github.service.converter.impl;

import com.hackerrank.github.repository.model.RepoEntity;
import com.hackerrank.github.service.converter.DomainConverter;
import com.hackerrank.github.service.domain.Repo;
import org.springframework.stereotype.Component;

@Component
public class RepoConverter implements DomainConverter<Repo, RepoEntity> {
    @Override
    public Repo toDomain(RepoEntity entity) {
        return new Repo(entity.getId(), entity.getName(), entity.getUrl());
    }

    @Override
    public RepoEntity toEntity(Repo domain) {
        return new RepoEntity(domain.getId(), domain.getName(), domain.getUrl());
    }
}
