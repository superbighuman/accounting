package ru.sfedu.accounting.Models;

import java.util.Date;

public class Technic {
    private String individualNumber;
    private String type;
    private boolean isComputer;
    private Date created;
    private Date updated;
    private String workingPlace;
    private String ownerINN;

    // Геттеры
    public String getIndividualNumber() {
        return individualNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public String getOwnerINN() {
        return ownerINN;
    }

    // Сеттеры
    public void setIndividualNumber(String individualNumber) {
        this.individualNumber = individualNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComputer(boolean isComputer) {
        this.isComputer = isComputer;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public void setOwnerINN(String ownerINN) {
        this.ownerINN = ownerINN;
    }
}
