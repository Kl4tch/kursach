package com.oxak.kursach.models;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;

// TODO не до конца вкурил че Serializable
@Entity
public class OrderDelivery implements Serializable {

    @OneToOne
    @JoinColumn(nullable = false)
    @Id
//    @PrimaryKeyJoinColumn()
    private Order orderId;

    private String address;
    private int deliveryCost;
    private long preferDeliveryTm;
}