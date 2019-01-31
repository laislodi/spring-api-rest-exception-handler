package com.hackerrank.github.service.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface DomainConverter<D, E> {

    D toDomain(E entity);

    E toEntity(D domain);

    default List<E> toEntity(Collection<D> domains) {
        Stream<D> dStream = domains.stream();
        Stream<E> eStream = dStream.map(this::toEntity);
        return eStream.collect(Collectors.toList());
    }

    default List<D> toDomain(Collection<E> entities) {
        Stream<E> eStream = entities.stream();
        Stream<D> dStream = eStream.map(this::toDomain);
        return dStream.collect(Collectors.toList());
    }

}
