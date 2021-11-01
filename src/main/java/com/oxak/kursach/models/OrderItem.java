package com.oxak.kursach.models;

import javax.persistence.*;

@Entity
public class OrderItem extends DictionaryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Order orderId;

    @OneToOne
    @JoinColumn(nullable = false)
    private Product itemId;

    private int quantity;
}