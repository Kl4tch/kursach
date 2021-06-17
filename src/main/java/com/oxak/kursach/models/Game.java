package com.oxak.kursach.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {
    // Long - потому что видимо дальше будет проще работать с полем как null
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private boolean rusText;
    private boolean rusVoice;

    public boolean isRusText() { return rusText; }
    public void setRusText(boolean rusText) { this.rusText = rusText; }

    public boolean isRusVoice() { return rusVoice; }
    public void setRusVoice(boolean rusVoice) { this.rusVoice = rusVoice; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}