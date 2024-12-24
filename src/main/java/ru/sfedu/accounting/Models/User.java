package ru.sfedu.accounting.Models;

import java.util.Date;

public class User {
    private String INN;
    private String name;
    private String surname;
    private String workingPlace;
    private Date created;
    private Date updated;

    // Геттеры
    public String getINN() {
        return INN;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    // Сеттеры
    public void setINN(String INN) {
        this.INN = INN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
