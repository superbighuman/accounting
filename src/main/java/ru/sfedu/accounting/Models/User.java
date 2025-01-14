package ru.sfedu.accounting.Models;

import ru.sfedu.accounting.PostgresAPI.Create;
import ru.sfedu.accounting.PostgresAPI.PostgresCRUD;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class User extends PostgresCRUD implements Model{
    private String INN;
    private String name;
    private String surname;
    private String workingPlace;
    private Date created;
    private Date updated;
    private Create userCreate;
    public User(String INN, String name, String surname, String workingPlace){
        super("Users");
        this.INN = INN;
        this.name = name;
        this.surname = surname;
        this.workingPlace = workingPlace;
    }

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

    @Override
    public boolean insertRecord() {
        boolean result = postgresCreate.insertRecord(this);
        return result;
    }

    @Override
    public boolean deleteRecord() {
        return false;
    }

    @Override
    public boolean updateRecord() {
        boolean result = postgresUpdate.updateRecord(this);
        return result;
    }

    @Override
    public boolean exists() {
        ResultSet resultSet = postgresRead.where("INN", "INN",INN).get();
        try {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(INN))
                    return true;
            }
        }
        catch (Exception e){
            Model.logger.info(e);
        }
        return false;
    }

//    @Override
//    public List<String> getFields() {
//        return getFields();
//    }
}
