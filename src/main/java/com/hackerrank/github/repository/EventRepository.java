package com.hackerrank.github.repository;

import com.hackerrank.github.model.EventEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends org.springframework.data.repository.CrudRepository<EventEntity, Long> {

    void deleteAll();

    List<EventEntity> findAll();

    @Query("select e from EventEntity e inner join fetch e.actorEntity a inner join fetch e.repoEntity where a.id = :actorID order by a.id")
    List<EventEntity> findByActorId(Long actorID);

    EventEntity findOne(Long id);

    void delete(Long id);
}
