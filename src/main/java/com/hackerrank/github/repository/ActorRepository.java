package com.hackerrank.github.repository;

import com.hackerrank.github.model.ActorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends org.springframework.data.repository.CrudRepository<ActorEntity, Long> {

    ActorEntity findOne(Long id);

    List<ActorEntity> findAll();

    void deleteAll();

    void delete(Long actorID);

//    @Query("select a from ActorEntity inner join EventEntity e ")
//    List<ActorEntity> findAllActorsSortByEventNumber();



//    @Query("select ")
//    List<ActorEntity> findAllOrderByEventNumberAsc();

}
