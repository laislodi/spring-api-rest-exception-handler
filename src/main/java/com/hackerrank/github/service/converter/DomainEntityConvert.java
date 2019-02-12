package com.hackerrank.github.service.converter;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface DomainEntityConvert<D,E> {

    E toEntity(D domain);

    D toDomain(E entity);

    default List<E> toEntity(Collection<D> domainList) {
        return domainList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    default List<D> toDomain(Collection<E> entityList) {
        return entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
