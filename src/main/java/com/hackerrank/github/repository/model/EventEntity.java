package com.hackerrank.github.repository.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.time.LocalDateTime;

@Entity
public class EventEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @JoinColumn(name = "ID_ACTOR")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ActorEntity actorEntity;

    @JoinColumn(name = "ID_REPO")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RepoEntity repoEntity;

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
