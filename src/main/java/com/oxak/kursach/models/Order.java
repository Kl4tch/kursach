package com.oxak.kursach.models;

import javax.persistence.*;

@Entity
public class Order extends DictionaryItem {

    @ManyToOne
    @JoinColumn
    private Customer customerId;

    private boolean finished;
    private int bonusUsed;
    private String note;

    private long createTm;
    private long updateTm;
}