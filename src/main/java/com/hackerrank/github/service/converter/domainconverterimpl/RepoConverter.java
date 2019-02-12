package com.hackerrank.github.service.converter.domainconverterimpl;

import com.hackerrank.github.model.RepoEntity;
import com.hackerrank.github.service.converter.DomainEntityConvert;
import com.hackerrank.github.service.domain.Repo;
import org.springframework.stereotype.Component;

@Component
public class RepoConverter implements DomainEntityConvert<Repo, RepoEntity> {
    @Override
    public RepoEntity toEntity(Repo repo) {
        return new RepoEntity(repo.getId(),
                repo.getName(),
                repo.getUrl());
    }

    @Override
    public Repo toDomain(RepoEntity repoEntity) {
        return new Repo(repoEntity.getId(),
                repoEntity.getName(),
                repoEntity.getUrl());
    }
}
