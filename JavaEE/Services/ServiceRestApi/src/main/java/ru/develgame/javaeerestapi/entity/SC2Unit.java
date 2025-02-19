package ru.develgame.javaeerestapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SC2_UNITS")
public class SC2Unit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ATTACK", nullable = false)
    private Double attack;

    @Column(name = "DEFENCE", nullable = false)
    private Double defense;

    public SC2Unit() {
    }

    public SC2Unit(int id, String name, Double attack, Double defense) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAttack() {
        return attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getDefense() {
        return defense;
    }

    public void setDefense(Double defense) {
        this.defense = defense;
    }
}
