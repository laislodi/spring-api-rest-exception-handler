package com.hackerrank.github.service.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {

    private Long id;
    private String type;
    private LocalDateTime createdAt;
    private Actor actor;
    private Repo repo;

    public Event() {
    }

    public Event(Long id, String type, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                ", actor=" + actor +
                ", repo=" + repo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(type, event.type) &&
                Objects.equals(createdAt, event.createdAt) &&
                Objects.equals(actor, event.actor) &&
                Objects.equals(repo, event.repo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, createdAt, actor, repo);
    }
}

