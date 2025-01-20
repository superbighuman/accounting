package ru.sfedu.accounting.Models;

import ru.sfedu.accounting.PostgresAPI.PostgresCRUD;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

public class Repair extends PostgresCRUD implements Model {
    private String individualNumber;
    private String repairService;
    private Date created;
    private Date updated;
    private String status;
    private static final String PRIMARY_KEY = "individualNumber";
    private static final String REPAIR_RELATION = "repair";
    Repair(String individualNumber, String repairService, String status){
        super(REPAIR_RELATION);
        this.individualNumber = individualNumber;
        this.repairService = repairService;
        this.status = status;
    }
    // Геттеры
    public String getIndividualNumber() {
        return individualNumber;
    }

    public String getRepairService() {
        return repairService;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getStatus() {
        return status;
    }

    // Сеттеры
    public void setIndividualNumber(String individualNumber) {
        this.individualNumber = individualNumber;
    }

    public void setRepairService(String repairService) {
        this.repairService = repairService;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setStatus(String status) {
        this.status = status;
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
