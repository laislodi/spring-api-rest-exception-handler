package com.hackerrank.github.repository;

import com.hackerrank.github.model.RepoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRepository extends org.springframework.data.repository.CrudRepository<RepoEntity, Long> {
    void deleteAll();

    void delete(Long repoID);

}
