package com.hackerrank.github.repository;

import com.hackerrank.github.repository.model.RepoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRepository extends org.springframework.data.repository.Repository<RepoEntity, Long>{
}
