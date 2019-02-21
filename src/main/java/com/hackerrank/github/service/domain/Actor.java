package com.hackerrank.github.service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Actor {

    private Long id;
    private String login;
    @JsonProperty("avatar_url")
    private String avatarUrl;

    public Actor() {
    }

    public Actor(Long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
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

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id) &&
                Objects.equals(login, actor.login) &&
                Objects.equals(avatarUrl, actor.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, avatarUrl);
    }
}
