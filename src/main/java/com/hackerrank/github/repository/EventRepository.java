package com.hackerrank.github.repository;

import com.hackerrank.github.repository.model.EventEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends org.springframework.data.repository.Repository<EventEntity, Long>{

    void deleteAll();

    EventEntity findOne(Long id);

    List<EventEntity> findAll();

    void save(EventEntity event);

    List<EventEntity> findByActorEntityId(Long id);
}
