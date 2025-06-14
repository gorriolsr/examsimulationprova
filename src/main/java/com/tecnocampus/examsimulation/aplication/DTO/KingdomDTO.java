package com.tecnocampus.examsimulation.aplication.DTO;

import java.time.LocalDateTime;

public class KingdomDTO {
    private Long id;
    private int gold;
    private int citizens;
    private int food;
    private LocalDateTime dateOfCreation;

    public KingdomDTO() {
    }

    public KingdomDTO(Long id, int gold, int citizens, int food, LocalDateTime dateOfCreation) {
        this.id = id;
        this.gold = gold;
        this.citizens = citizens;
        this.food = food;
        this.dateOfCreation = dateOfCreation;
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
