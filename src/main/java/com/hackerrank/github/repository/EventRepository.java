package com.hackerrank.github.repository;

import com.hackerrank.github.model.EventEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends org.springframework.data.repository.CrudRepository<EventEntity, Long> {

    void deleteAll();

    List<EventEntity> findAll();

    @Query("select e from EventEntity e inner join fetch e.actorEntity a inner join fetch e.repoEntity where a.id = :actorId order by a.id")
    List<EventEntity> findByActorId(@Param("actorId") Long actorID);

//    @Query("select e from EventEntity e inner join e.actorEntity a where  = order by e.createdAt desc ")
//    List<EventEntity> findEventEntityByActorEntity_IdOrderByCreatedAt(Long actorId);

    EventEntity findOne(Long id);

    void delete(Long id);
}
