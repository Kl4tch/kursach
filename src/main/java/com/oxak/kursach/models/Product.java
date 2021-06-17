package com.oxak.kursach.models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Game game;

    private double price;
    private int remain;
    private boolean isCollectionEdition;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}