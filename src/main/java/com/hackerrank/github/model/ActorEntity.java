package com.hackerrank.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACTOR")
public class ActorEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "AVATAR_URL")
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @OneToMany(mappedBy = "actorEntity")
    private List<EventEntity> eventEntityList;

    public ActorEntity() {
    }

    public ActorEntity(Long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.eventEntityList = new ArrayList<>();
    }

    public ActorEntity(Long id, String login, String avatarUrl, List<EventEntity> eventEntityList) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.eventEntityList = eventEntityList;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<EventEntity> getEventEntityList() {
        return eventEntityList;
    }

    public void setEventEntityList(List<EventEntity> eventEntityList) {
        this.eventEntityList = eventEntityList;
    }
}
