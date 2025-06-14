package com.tecnocampus.examsimulation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("KINGDOM")
public class Kingdom {

    @Id
    private Long id;
    private int gold;
    private int citizens;
    private int food;
    private LocalDateTime dateOfCreation;

    public Kingdom() {
        // For Spring Data
    }

    public Kingdom(int gold, int citizens, int food) {
        this.gold = gold;
        this.citizens = citizens;
        this.food = food;
        this.dateOfCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCitizens() {
        return citizens;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
