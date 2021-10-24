package com.oxak.kursach.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order extends DictionaryItem {

    @ManyToOne
    @JoinColumn(nullable = true)
    private Customer customerId;

    private boolean finished;
    private int bonusUsed;
}