package com.hackerrank.github.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENT")
public class EventEntity {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "ID_ACTOR")
    @ManyToOne(cascade = CascadeType.ALL)
    private ActorEntity actorEntity;
    @JoinColumn(name = "ID_REPO")
    @ManyToOne(cascade = CascadeType.ALL)
    private RepoEntity repoEntity;
    @Column(name = "CREATED_AT")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public EventEntity() {
    }

    public EventEntity(Long id, String type, ActorEntity actorEntity, RepoEntity repoEntity, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.actorEntity = actorEntity;
        this.repoEntity = repoEntity;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActorEntity getActorEntity() {
        return actorEntity;
    }

    public void setActorEntity(ActorEntity actorEntity) {
        this.actorEntity = actorEntity;
    }

    public RepoEntity getRepoEntity() {
        return repoEntity;
    }

    public void setRepoEntity(RepoEntity repoEntity) {
        this.repoEntity = repoEntity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
