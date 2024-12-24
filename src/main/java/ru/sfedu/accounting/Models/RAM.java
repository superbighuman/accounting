package ru.sfedu.accounting.Models;

import java.util.Date;

public class RAM {
    private String individualNumber;
    private String name;
    private double memory;
    private String type;
    private double speed;
    private Date created;
    private Date updated;

    // Геттеры
    public String getIndividualNumber() {
        return individualNumber;
    }

    public String getName() {
        return name;
    }

    public double getMemory() {
        return memory;
    }

    public String getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    // Сеттеры
    public void setIndividualNumber(String individualNumber) {
        this.individualNumber = individualNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
