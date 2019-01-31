package com.hackerrank.github.repository;

import com.hackerrank.github.repository.model.ActorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends org.springframework.data.repository.Repository<ActorEntity, Long>{

    ActorEntity findOne(Long id);

    void save(ActorEntity actor);

    List<ActorEntity> findAll();
}
