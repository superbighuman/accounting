package ru.sfedu.accounting.Models;

import ru.sfedu.accounting.PostgresAPI.PostgresCRUD;

import java.sql.ResultSet;
import java.util.Map;

public class RepairService extends PostgresCRUD implements Model {
    private String name;
    private String address;
    private static final String PRIMARY_KEY = "name";
    private static final String REPAIR_SERVICE_RELATION = "RepairService";

    RepairService(String name, String address){
        super(REPAIR_SERVICE_RELATION);
        this.name = name;
        this.address = address;
    }
    // Геттеры
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
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
