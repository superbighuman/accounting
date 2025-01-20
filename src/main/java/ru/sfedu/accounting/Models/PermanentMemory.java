package ru.sfedu.accounting.Models;

import ru.sfedu.accounting.PostgresAPI.PostgresCRUD;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

public class PermanentMemory extends PostgresCRUD implements Model {
    private String individualNumber;
    private String name;
    private double memory;
    private String type;
    private double speed;
    private Date created;
    private Date updated;

    private final static String PRIMARY_KEY = "individualNumber";
    private final static String PERMANENT_MEMORY_RELATION = "PermanentMemory";
    PermanentMemory(String individualNumber, String name, double memory, String type, double speed){
        super(PERMANENT_MEMORY_RELATION);
        this.individualNumber = individualNumber;
        this.name = name;
        this.memory = memory;
        this.type = type;
        this.speed = speed;
    }
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
        String key = keyGet();
        Map<String, String> map = getItems();
        String keyValue = map.get(key);
        ResultSet resultSet = postgresRead.where(key, key, keyValue).get();
        try {
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(keyValue))
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
        return PRIMARY_KEY;
    }
}
