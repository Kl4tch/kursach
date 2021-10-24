package com.oxak.kursach.models;

import javax.persistence.*;

@Entity
public class Customer extends DictionaryItem {

    private int bonuses;

    public int getBonuses() { return bonuses; }
    public void setBonuses(int bonuses) { this.bonuses = bonuses;}
}