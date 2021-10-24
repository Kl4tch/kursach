package com.oxak.kursach.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DictionaryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private long createTm;
    private long updateTm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public long getCreateTm() { return createTm; }
    public void setCreateTm(long createTm) { this.createTm = createTm; }

    public long getUpdateTm() { return updateTm; }
    public void setUpdateTm(long updateTm) { this.updateTm = updateTm; }
}
