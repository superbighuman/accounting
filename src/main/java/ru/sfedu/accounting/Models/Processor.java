package ru.sfedu.accounting.Models;

import java.util.Date;

public class Processor {
    private String individualNumber;
    private String name;
    private double speed;
    private int cores;
    private Date created;
    private Date updated;

    // Геттеры
    public String getIndividualNumber() {
        return individualNumber;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public int getCores() {
        return cores;
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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
