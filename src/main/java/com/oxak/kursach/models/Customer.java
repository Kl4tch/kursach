package com.oxak.kursach.models;

import javax.persistence.*;

@Entity
public class Customer extends DictionaryItem {

    private int bonuses;
    private String phone;

    public int getBonuses() { return bonuses; }
    public void setBonuses(int bonuses) { this.bonuses = bonuses;}
}