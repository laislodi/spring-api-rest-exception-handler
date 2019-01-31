package com.hackerrank.github.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class RepoEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "URL")
    private String url;

    public RepoEntity() {
    }

    public RepoEntity(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
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
}
