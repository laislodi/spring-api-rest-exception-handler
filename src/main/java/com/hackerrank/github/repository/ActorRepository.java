package com.hackerrank.github.repository;

import com.hackerrank.github.model.ActorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends org.springframework.data.repository.CrudRepository<ActorEntity, Long> {

    ActorEntity findOne(Long id);

    List<ActorEntity> findAll();

//    @Query("select a from ")
//    List<ActorEntity> findAllActorsWithOrderedEventList();

    void deleteAll();

    void delete(Long actorID);

    @Query("select a from ActorEntity a left join a.eventEntityList e " +
            "group by a.id, a.login, a.avatarUrl " +
            "order by count(e.id) desc, max(e.createdAt) desc, a.login asc")
    List<ActorEntity> allActorsOrderByNumberOfEventsDescMaxCreatedAtDescLoginAsc();

    ActorEntity findById(Long actorId);
}
