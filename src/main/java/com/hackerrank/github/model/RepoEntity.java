package com.hackerrank.github.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REPO")
public class RepoEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "URL")
    private String url;
    @OneToMany(mappedBy = "repoEntity")
    private List<EventEntity> eventEntityList;

    public RepoEntity() {
    }

    public RepoEntity(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.eventEntityList = new ArrayList<>();
    }

    public RepoEntity(Long id, String name, String url, List<EventEntity> eventEntityList) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.eventEntityList = eventEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<EventEntity> getEventEntityList() {
        return eventEntityList;
    }

    public void setEvent(List<EventEntity> eventEntityList) {
        this.eventEntityList = eventEntityList;
    }

}
