package ru.sfedu.accounting.Models;

import ru.sfedu.accounting.PostgresAPI.PostgresCRUD;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Request extends PostgresCRUD implements Model{
    private UUID id;
    private String body;
    private String status;
    private Date created;
    private Date updated;
    private String ownerINN;
    private String managerINN;
    private final static String PRIMARY_KEY = "id";
    public final static String REQUEST_RELATION = "request";
    public Request(String body, String status, String ownerINN, String managerINN){
        super(REQUEST_RELATION);
        this.body = body;
        this.status = status;
        this.ownerINN = ownerINN;
        this.managerINN = managerINN;
        this.id = UUID.randomUUID();

    }

    public String keyGet(){
        return id.toString();
    }

    // Геттеры
    public UUID getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getOwnerINN() {
        return ownerINN;
    }

    public String getManagerINN() {
        return managerINN;
    }

    // Сеттеры
    public void setId(UUID id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setOwnerINN(String ownerINN) {
        this.ownerINN = ownerINN;
    }

    public void setManagerINN(String managerINN) {
        this.managerINN = managerINN;
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
}
