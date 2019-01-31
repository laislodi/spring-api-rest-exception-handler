package com.hackerrank.github.repository.model;

import com.hackerrank.github.service.domain.Event;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ActorEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @OneToMany(mappedBy = "actorEntity")
    private List<EventEntity> eventList;

    public ActorEntity() {
    }

    public ActorEntity(Long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public ActorEntity(Long id, String login, String avatarUrl, List<EventEntity> eventList) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.eventList = eventList;
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

    public List<EventEntity> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventEntity> eventList) {
        this.eventList = eventList;
    }
}
