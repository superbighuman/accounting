package ru.sfedu.accounting.Models;

import ru.sfedu.accounting.PostgresAPI.PostgresCRUD;

import java.sql.ResultSet;
import java.util.Date;

public class Technic extends PostgresCRUD implements Model{
    private String individualNumber;
    private String type;
    private boolean isComputer;
    private String workingPlace;
    private String ownerINN;
    private Date created;
    private Date updated;

    protected static final String TECHNIC_RELATION = "Technic";
    protected final String PRIMARY_KEY = "individualNumber";
    Technic(String individualNumber, String type, boolean isComputer, String workingPlace, String ownerINN){
        super(TECHNIC_RELATION);
        this.individualNumber = individualNumber;
        this.type = type;
        this.isComputer = isComputer;
        this.workingPlace = workingPlace;
        this.ownerINN = ownerINN;
    }

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

    @Override
    public boolean insertRecord() {
        boolean result = postgresCreate.insertRecord(this);
        return result;
    }

    @Override
    public boolean deleteRecord() {
        boolean result = postgresDelete.deleteRecord(this);
        return result;
    }

    @Override
    public boolean updateRecord() {
        boolean result = postgresUpdate.updateRecord(this);
        return result;
    }

    @Override
    public boolean exists() {
        ResultSet resultSet = postgresRead.where(PRIMARY_KEY, PRIMARY_KEY,individualNumber).get();
        try {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(individualNumber))
                    return true;
            }
        }
        catch (Exception e){
            Model.logger.info(e);
        }
        return false;
    }

    @Override
    public String keyGet() {
        return individualNumber;
    }
}
